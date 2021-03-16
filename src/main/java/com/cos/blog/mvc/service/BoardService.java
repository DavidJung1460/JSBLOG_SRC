package com.cos.blog.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cos.blog.mvc.domain.Board;
import com.cos.blog.mvc.repository.BoardRepository;


/**
 * 게시판 Service
 * @author 정희진
 */
@Service
public class BoardService {
	
	@Autowired
	private BoardRepository repository;
	
	/**
	 * 목록 리턴
	 * @Return
	 */
	public List<Board> getList(){
		return repository.getList();
	};
	/**
	 * 상세 정보 리턴
	 * @Param boardSeq
	 * @Return 
	 */
	public Board get(int boardSeq) {
		return repository.get(boardSeq);
	};
	/**
	 * 등록 처리
	 * @Param board
	 * @Return 
	 */
	public void save(Board board) {
		repository.save(board);
	};
	
	/**
	 * 수정 처리
	 * @Param board
	 * @Return 
	 */
	public void update(Board board) {
		repository.update(board);
	};
	
	/**
	 * 삭제 처리
	 * @Param boardSeq
	 * @Return 
	 */
	public void delete(int boardSeq) {
		repository.delete(boardSeq);
	};
}
