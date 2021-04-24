package com.cos.blog.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BlogControllerTest {
	@GetMapping("/main/mybatistest")
	public String helloGet() {
		return "main";
	}
	@PostMapping("/main/mybatistest")
	public String helloPost() {
		return "main";
	}
}
