package com.cos.blog.message;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {
	
	//http://localhost:8000/blog/temp/home
	@GetMapping("/temp/jsp")
	public String tempHome() {
		return Messages.getString("TempControllerTest.0"); //$NON-NLS-1$
	}
}
