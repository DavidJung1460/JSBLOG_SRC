package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Board;
import com.cos.blog.model.PgmMenu;
import com.cos.blog.model.Reply;
import com.cos.blog.model.Search;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;
import com.cos.blog.repository.UserRepository;

//스프링이 컴포넌트 스캔으로 Bean에 등록해줌
@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private ReplyRepository replyRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional //Save 전체가 하나의 트랜잭션으로 묶임
	public void 글쓰기(Board board, User user) {
		if(board.getPrivateYn().equals("Y")) {
			String rawPassword = board.getBoardPass(); //원문
			String encPassword = encoder.encode(rawPassword); //해쉬화
			board.setBoardPass(encPassword);
		}
		
		board.setCount(0);
		board.setUser(user);
		
		System.out.println("글저장 컨텐츠 : "+board.getContents());
		boardRepository.save(board);
	}
	
	@Transactional(readOnly = true)
	public Page<Board> 글목록(Search search, PgmMenu pgmMenu, Pageable pageable) {
		Page<Board> board = null;
		
		if(search.getSearchText() != null) {
			if(search.getSearchSelect().equals("제목")) {
				board = boardRepository.findBytitleContainingAndNoticeYnAndPgmMenu(search.getSearchText(), "N", pgmMenu,  pageable);
			}else {
				board = boardRepository.findByNicknameAndPgmMenu("%"+search.getSearchText()+"%", "N", pgmMenu, pageable);
			}
		}else {
			board =  boardRepository.findByNoticeYnAndPgmMenu("N", pgmMenu,pageable);
		}
		return board;
	}
	
	@Transactional(readOnly = true)
	public Board 글상세보기(int id) {
		return boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 상세보기 실패 : 글 번호를 찾을 수 없습니다.");
				});
	}
	
	@Transactional
	public void 글삭제하기(int id) {
		boardRepository.deleteById(id);
	}

	@Transactional
	public void 글수정하기(int id, Board reqBoard) {
		//영속화 시키기 (영속성 컨텍스트 생성)
		Board board = boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 찾기 실패 : 글 번호를 찾을 수 없습니다.");
				});
		
		// 비공개 게시글 관련 수정 부분
		board.setPrivateYn(reqBoard.getPrivateYn());
		if(reqBoard.getPrivateYn().equals("Y")) {
			if(!reqBoard.getBoardPass().equals("") && reqBoard.getBoardPass() != null) {
				String rawPassword = reqBoard.getBoardPass(); //원문
				String encPassword = encoder.encode(rawPassword); //해쉬화
				board.setBoardPass(encPassword);
			}else {
			}
		}else {
			board.setBoardPass(reqBoard.getBoardPass());
		}
		//메인화면 현출 여부에 따른 수정부분
		board.setMainNoticeYn(reqBoard.getMainNoticeYn());
		if(reqBoard.getMainNoticeYn().equals("Y")) {
			if(reqBoard.getOriginFilename() != null) {
				if (!reqBoard.getOriginFilename().equals("")) {
					board.setOriginFilename(reqBoard.getOriginFilename());
					board.setFilename(reqBoard.getFilename());
					board.setFilePath(reqBoard.getFilePath());
				}
			}
		}else {
			board.setOriginFilename(reqBoard.getOriginFilename());
			board.setFilename(reqBoard.getFilename());
			board.setFilePath(reqBoard.getFilePath());
		}
		
		board.setSubject(reqBoard.getSubject());
		
		board.setNoticeYn(reqBoard.getNoticeYn());
		
		
		board.setTitle(reqBoard.getTitle());
		board.setContents(reqBoard.getContents());
		// 해당 함수로 종료시(Service가 종료될 때) 트랜잭션이 종료됩니다. 이때 더티체킹
		// 따라서 위 상황시 자동 업데이트(DB Flush)가 진행됨
	}

	@Transactional
	public void 댓글쓰기(User user, int boardId, Reply reqReply) {
		reqReply.setBoard(boardRepository.findById(boardId).orElseThrow(()->{
			return new IllegalArgumentException("댓글 쓰기 실패 : 글 번호를 찾을 수 없습니다.");
		}));
		reqReply.setUser(user);
		replyRepository.save(reqReply);
	}
	
	@Transactional
	public void 댓글삭제(int replyId) {
		replyRepository.deleteById(replyId);
	}
	@Transactional(readOnly = true)
	public Page<Board> 공지글목록(Search search, PgmMenu pgmMenu, Pageable pageable) {
		Page<Board> board = null;

		if (search.getSearchText() != null) {
			if (search.getSearchSelect().equals("제목")) {
				board = boardRepository.findBytitleContainingAndNoticeYnAndPgmMenu(search.getSearchText(), "Y", pgmMenu, pageable);
			} else {
				board = boardRepository.findByNicknameAndPgmMenu("%" + search.getSearchText() + "%", "Y", pgmMenu, pageable);
			}
		} else {
			board = boardRepository.findByNoticeYnAndPgmMenu("Y", pgmMenu,pageable);
		}
		return board;
	}

	public Page<Board> 메인글목록(Pageable pageable) {
		Page<Board> board = null;
		board = boardRepository.findByMainNoticeYn("Y", pageable);
		
		return board;
	}

	public int 비밀글비밀번호확인(Board reqBoard) {
		Board board = 글상세보기(reqBoard.getId());
		int boardPassChk = 0;
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		if(encoder.matches(reqBoard.getBoardPass(), board.getBoardPass())) {
			boardPassChk = 1;
		}
		if(board.getBoardPass().equals("")||board.getBoardPass() == null) {
			if(reqBoard.getBoardPass().equals("")||reqBoard.getBoardPass() == null)	boardPassChk=1;
		}
		return boardPassChk;
	}
}
