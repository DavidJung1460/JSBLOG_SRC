package com.cos.blog.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.mvc.domain.Board;
import com.cos.blog.mvc.service.BoardServiceMybatis;


/**
 * 게시판 Controller
 * @author 정희진
 */
@RestController
//@RequestMapping("/board/mybatis")
public class BoardMybatisController {
	
	@Autowired
	private BoardServiceMybatis boardService;
	
	/**
	 * 목록 리턴
	 * @Return
	 */
	/*
	 * @GetMapping public List<Board> getList(){ return boardService.getList(); };
	 */
	/**
	 * 상세 정보 리턴
	 * @Param boardSeq
	 * @Return 
	 */
	//@GetMapping("/{boardSeq}")
	/*
	 * public Board get(@PathVariable int boardSeq) { return
	 * boardService.get(boardSeq); };
	 */
	/**
	 * 등록/수정 처리
	 * @Param board
	 * @Return 
	 */
	//@GetMapping("/save")
	/*
	 * public void save(Board board) { boardService.save(board); };
	 */
	
	/**
	 * 수정 처리
	 * @Param board
	 * @Return 
	 */
	/*
	 * public void update(Board board) { boardService.update(board); };
	 */
	
	/**
	 * 삭제 처리
	 * @Param boardSeq
	 * @Return 
	 */
	//@GetMapping("/delete/{boardSeq}")
	/*
	 * public void delete(@PathVariable int boardSeq) {
	 * boardService.delete(boardSeq); };
	 */
}
