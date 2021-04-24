package com.cos.blog.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.configuration.auth.PrincipalDetail;
import com.cos.blog.model.Grade;
import com.cos.blog.model.PgmMenu;
import com.cos.blog.model.UseAuth;
import com.cos.blog.model.User;
import com.cos.blog.service.BlogService;
import com.cos.blog.service.UserService;

@RestController
public class PgmMenuApiController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/auth/left")
	public @ResponseBody List<PgmMenu> left(@AuthenticationPrincipal PrincipalDetail principal ) {
		Grade grade = new Grade();
		List<PgmMenu> left  = null;
		if(principal == null) {
			grade.setGradeCd(1);
			left = blogService.기본좌측메뉴리스트(grade);
		} else {
			grade = principal.getUser().getGrade();
			left = blogService.좌측메뉴리스트(grade);
		}
		
		return left;
	}
	@PostMapping("/auth/leftAuthDate")
	public @ResponseBody UseAuth leftAuthDate(@AuthenticationPrincipal PrincipalDetail principal ) {
		UseAuth useAuth = new UseAuth();
		
		if(principal != null) {
			User user = principal.getUser();
			useAuth = userService.사용권한조회(user);
			return useAuth;
		} 		
		return null;
		
	}
}
