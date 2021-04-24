package com.cos.blog.controller.api;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cos.blog.configuration.auth.PrincipalDetail;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.PgmMenu;
import com.cos.blog.model.Reply;
import com.cos.blog.service.BoardService;

@RestController
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;
	
	/*
	 * @PostMapping("/api/board") public ResponseDto<Integer> save(@RequestBody
	 * Board board,@AuthenticationPrincipal PrincipalDetail principal) {
	 * 
	 * boardService.글쓰기(board,principal.getUser());
	 * 
	 * return new ResponseDto<Integer>(HttpStatus.OK.value(),1); }
	 */
	
	@PostMapping("/api/board/{pgmCd}")
	public ResponseDto<Integer> save(@RequestParam("file") MultipartFile files,
			/* @RequestBody */ Board board,@AuthenticationPrincipal PrincipalDetail principal, @PathVariable int pgmCd) {
		PgmMenu pgmMenu = new PgmMenu();
		pgmMenu.setPgmCd(pgmCd);
		board.setPgmMenu(pgmMenu);;
		try {
            String originFilename = files.getOriginalFilename();
            //String filename = new MD5Generator(originFilename).toString();
            //메인의 이미지 업로드를 위한 임시조치 파일업로드 구현시 MD5로 변경 필요
            String filename = files.getOriginalFilename();
            /* 실행되는 위치의 'files' 폴더에 파일이 저장됩니다. */
            String savePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
            /* 파일이 저장되는 폴더가 없으면 폴더를 생성합니다. */
            if (!new File(savePath).exists()) {
                try{
                    new File(savePath).mkdir();
                }
                catch(Exception e){
                    e.getStackTrace();
                }
            }
            String filePath = savePath + "\\" + filename;
            if(originFilename!=null) {
            	if(!originFilename.equals("")) {
            		files.transferTo(new File(filePath));
            		board.setOriginFilename(originFilename);
                    board.setFilename(filename);
                    board.setFilePath(filePath);
            	}
            }
            
            
            
            
            boardService.글쓰기(board,principal.getUser());
/*            Long fileId = fileService.saveFile(fileDto);
            board.setFileId(fileId);
            boardService.savePost(boardDto);*/
        } catch(Exception e) {
            e.printStackTrace();
        }
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id,@AuthenticationPrincipal PrincipalDetail principal){
		Board board = boardService.글상세보기(id);
		if(board.getUser().getId() == principal.getUser().getId()) {
			boardService.글삭제하기(id);
		}
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer> save(@PathVariable int id,
			@RequestParam("file") MultipartFile files,Board board/* , @AuthenticationPrincipal PrincipalDetail principal */) {
		//if(board.getUser().getId() == principal.getUser().getId()) {
			//boardService.글수정하기(id,reqBoard);
		//}
		try {
            String originFilename = files.getOriginalFilename();
            //String filename = new MD5Generator(originFilename).toString();
            //메인의 이미지 업로드를 위한 임시조치 파일업로드 구현시 MD5로 변경 필요
            String filename = files.getOriginalFilename();
            /* 실행되는 위치의 'files' 폴더에 파일이 저장됩니다. */
            String savePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
            /* 파일이 저장되는 폴더가 없으면 폴더를 생성합니다. */
            if (!new File(savePath).exists()) {
                try{
                    new File(savePath).mkdir();
                }
                catch(Exception e){
                    e.getStackTrace();
                }
            }
            String filePath = savePath + "\\" + filename;
            if(originFilename!=null) {
            	if(!originFilename.equals("")) {
            		files.transferTo(new File(filePath));
            		board.setOriginFilename(originFilename);
                    board.setFilename(filename);
                    board.setFilePath(filePath);
            	}
            }
            
            
          
            
            boardService.글수정하기(id,board);
/*            Long fileId = fileService.saveFile(fileDto);
            board.setFileId(fileId);
            boardService.savePost(boardDto);*/
        } catch(Exception e) {
            e.printStackTrace();
        }
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	//데이터 받을 때 컨트롤러에서 dto를 만들어서 받는게 좋다.
	//dto 사용하지 않은 이유는 객체의 관리 포인트가 업무 로직별로 기하급수적으로 증가할 수 있기 때문
	@PostMapping("/api/board/{boardId}/reply")
	public ResponseDto<Integer> replySave(@PathVariable int boardId,@RequestBody Reply reply,@AuthenticationPrincipal PrincipalDetail principal) {
		
		
		reply.setUser(principal.getUser());
		boardService.댓글쓰기(principal.getUser(), boardId, reply);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@DeleteMapping("/api/board/{boardId}/reply/{replyId}")
	public ResponseDto<Integer> replyDelete(@PathVariable int replyId){
		
		boardService.댓글삭제(replyId);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@PutMapping("/api/board/{boardId}/reply/{replyId}")
	public ResponseDto<Integer> replyUpdate(@PathVariable int replyId, @RequestBody Reply reply){
		
		//boardService.댓글수정(replyId);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@PostMapping("/api/board/boardPassChk")
	public String boardPassChk(@RequestBody Board board) {
		String message = "";
		if(boardService.비밀글비밀번호확인(board) == 1) {
			message="일치";
		} else {
			message="불일치";
		}
		
		return message;
	}
}
