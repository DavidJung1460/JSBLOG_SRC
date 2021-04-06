package com.cos.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;

@ControllerAdvice//모든 Exception이 발생했을때 해당 클래스로 들어오도록 연결
@RestController
public class GlobalExceptionHandler {
	//exception 은 자유롭게 추가가능
	//화면으로 전환이 필요하면 상단 RestController 를 에러 화면으로 전송하면 됨. (메시지 포함 에러화면 전송)
	@ExceptionHandler(value=IllegalArgumentException.class) 
	public ResponseDto<String> handleAgumentException(IllegalArgumentException e) {
		System.out.println("global 핸들러 진입##########");
		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(),"");
	}
}
