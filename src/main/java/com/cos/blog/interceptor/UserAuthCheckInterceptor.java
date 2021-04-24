package com.cos.blog.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cos.blog.model.UseAuth;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

/*스프링 시큐리티때문에 인터셉터가 작동안하는것으로 추정...*/
@Configuration
public class UserAuthCheckInterceptor implements HandlerInterceptor{
	@Autowired
	private UserService userService;
	
	
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
		//User user = new User();
		//System.out.println("인터셉터 진입이다.");
		//UseAuth useAuth = userService.사용권한조회(null);
		/*
		 * if(ObjectUtils.isEmpty(loginVO)){ response.sendRedirect("/moveLogin.go");
		 * return false; }else{ session.setMaxInactiveInterval(30*60); return true; }
		 */
    	
        return true;
    }
 
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub
    }
 
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub
    }
 
}