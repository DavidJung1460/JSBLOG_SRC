package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.configuration.auth.PrincipalDetail;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.service.BoardService;

@RestController
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;
	
	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board,@AuthenticationPrincipal PrincipalDetail principal) {
		
		boardService.글쓰기(board,principal.getUser());
		
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
			@RequestBody Board board/* , @AuthenticationPrincipal PrincipalDetail principal */) {
		System.out.println("id : "+id);
		//System.out.println("principal : "+principal.getUser().getId());
		System.out.println("board : "+board.toString());
		
		boardService.글수정하기(id,board);
		//if(board.getUser().getId() == principal.getUser().getId()) {
			//boardService.글수정하기(id,reqBoard);
		//}
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
			
}
