package com.cos.blog.filter;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.cos.blog.configuration.auth.PrincipalDetail;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;


/**
 * @author 정희진
 * @임시 필터 추후 응용하여 사용 필요 필터는 WebConfiguration.java 에 순차 명시
 */
public class HelloFilter implements Filter
{
	@Autowired
	private UserService userService;
	
	public void doFilter(ServletRequest req,
			ServletResponse res/* ,@AuthenticationPrincipal PrincipalDetail principal */, FilterChain chain)
			throws IOException, ServletException
	{
		
		// 필터적용
		//((HttpServletResponse)res).setHeader("HelloHeader", "test");
		/*
		 * HttpServletRequest httpRequest = (HttpServletRequest)req; HttpSession session
		 * = httpRequest.getSession(true); if(session != null) {
		 * 
		 * PrincipalDetail principal = (PrincipalDetail)
		 * session.getAttribute("principal"); if(principal != null) { User user =
		 * principal.getUser(); if(user == null) {
		 * System.out.println("#####username : "+user.getUsername()); }else {
		 * System.out.println("USER VALUE NULL"); } } else {
		 * System.out.println("객체가 없다니까.."); } }
		 */
		
		chain.doFilter(req, res);
	}

	@Override
	public void destroy()
	{
	}

	@Override
	public void init(FilterConfig fc) throws ServletException
	{
	}
}