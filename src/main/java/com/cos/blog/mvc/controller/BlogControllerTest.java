package com.cos.blog.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BlogControllerTest {
	@GetMapping("/main")
	public String helloGet() {
		System.out.println("get 요청 진입");
		return "test";
	}
	@PostMapping("/main")
	public String helloPost() {
		System.out.println("post 요청 진입");
		return "test";
	}
}
