package com.cos.blog.controller.api;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.configuration.auth.PrincipalDetail;
import com.cos.blog.configuration.auth.PrincipalDetailService;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.enumtype.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	/*
	 * @Autowired private HttpSession loginSession;
	 */
	
	@Autowired
	PrincipalDetailService principalDetailService;
	
	@PostMapping("/auth/um/joinProc")
	public ResponseDto<Integer> joinProc(@RequestBody User user) {
		
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	//Ajax 요청시 객체를 JSON으로 받고 싶으면 @RequestBody 를 사용하나
	//일반적인 Form 요청일 경우 어노테이션을 기입하지 않고 객체로 받으면 된다.
	@PutMapping("/um/userUpdateProc")
	public ResponseDto<Integer> userUpdateProc(@RequestBody User user,HttpSession session) {
		
		userService.회원수정(user);
		
		//시큐리티 세션값 변경(수정 후 최신화) - DB에서 다시 조회해서 시큐리티에서 세션을 다시 SET 한다.
		UserDetails userDetail = principalDetailService.loadUserByUsername(user.getUsername());
		Authentication authentication =
				new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
		
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(authentication);
		session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	
	/*
	 * @PostMapping("/api/um/user/login") public ResponseDto<Integer>
	 * login(@RequestBody User user) {
	 * System.out.println("UserApiController : login 호출됨"); User principal =
	 * userService.로그인(user); //principal (접근주체)
	 * 
	 * if(principal != null) { loginSession.setAttribute("principal", principal); }
	 * 
	 * return new ResponseDto<Integer>(HttpStatus.OK.value(),1); }
	 */
	
}
