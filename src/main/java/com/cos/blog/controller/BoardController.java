package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.blog.model.PgmMenu;
import com.cos.blog.model.Search;
import com.cos.blog.service.BlogService;
import com.cos.blog.service.BoardService;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@Autowired BlogService blogService;
	
	@GetMapping("/board/{id}")
	public String findById(Model model,@PathVariable int id) {
		model.addAttribute("board",boardService.글상세보기(id));
		System.out.println("글 상세보기 컨텐츠 : "+boardService.글상세보기(id).getContents());
		return "board/boardDetail";
	}
	
	//USER 권한이 필요
	@GetMapping("/board/{id}/updateForm")
	public String updateForm(@PathVariable int id,Model model) {
		model.addAttribute("board",boardService.글상세보기(id));
		
		return "board/updateForm";
	}
	
	//USER 권한이 필요
	@GetMapping("/board/saveForm/{pgmCd}")
	public String saveForm(Model model,@PathVariable int pgmCd) {
		model.addAttribute("pgmCd", pgmCd);
		return "board/saveForm";
	}
	
	@GetMapping("/board/listForm/{pgmCd}")
	public String boardList(@PathVariable int pgmCd,/* @AuthenticationPrincipal PrincipalDetail principal */Model model ,Search search,@PageableDefault(size=10,sort="id",direction =  Direction.DESC) Pageable pageable) {
		/*
		 * if(principal != null) {
		 * System.out.println("G.로그인 사용자 아이디 : "+principal.getUsername()); } else {
		 * System.out.println("미로그인 사용자"); }
		 */
		PgmMenu pgmMenu = new PgmMenu();
		pgmMenu.setPgmCd(pgmCd);
		
		model.addAttribute("noticeBoards", boardService.공지글목록(search,pgmMenu,pageable));
		model.addAttribute("boards", boardService.글목록(search,pgmMenu,pageable));
		model.addAttribute("search",search);
		model.addAttribute("pgmCd", pgmCd);
		model.addAttribute("userUseYn", blogService.프로그램메뉴조회(pgmCd).getUserUseYn());
		return "board/boardList";
	}
}
