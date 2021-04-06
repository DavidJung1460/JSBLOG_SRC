package com.cos.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import com.cos.blog.service.BoardService;

@Controller
public class BlogController {
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/main")
	public String helloGet(/* @AuthenticationPrincipal PrincipalDetail principal */Model model ,@PageableDefault(size=3,sort="id",direction =  Direction.DESC) Pageable pageable) {
		/*
		 * if(principal != null) {
		 * System.out.println("G.로그인 사용자 아이디 : "+principal.getUsername()); } else {
		 * System.out.println("미로그인 사용자"); }
		 */
		model.addAttribute("boards", boardService.글목록(pageable));
		return "cm/main";
	}
	@PostMapping("/main")
	public String helloPost(/* @AuthenticationPrincipal PrincipalDetail principal */Model model,@PageableDefault(size=3,sort="id",direction =  Direction.DESC) Pageable pageable) {
		/*
		 * if(principal != null) {
		 * System.out.println("P.로그인 사용자 아이디 : "+principal.getUsername()); } else {
		 * System.out.println("미로그인 사용자"); }
		 */
		model.addAttribute("boards", boardService.글목록(pageable));
		return "cm/main";
	}
}
