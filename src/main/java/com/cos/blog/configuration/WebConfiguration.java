package com.cos.blog.configuration;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cos.blog.filter.HTMLCharacterEscapes;
import com.cos.blog.filter.HelloFilter;
import com.cos.blog.interceptor.UserAuthCheckInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

	/** 
	 * Add Index Page
	 */
	@Override

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("forward:/index.jsp");
	}
	
	/** 
	 * Lucy XSS Filter & json 형태의 requestBody 전달에는 적용 불가 
	 */
	
	// 이거 들어가면 메인/디테일 현출 불가
	/*
	 * @Bean public FilterRegistrationBean<XssEscapeServletFilter>
	 * filterRegistrationBean() { FilterRegistrationBean<XssEscapeServletFilter>
	 * filterRegistration = new FilterRegistrationBean<>();
	 * filterRegistration.setFilter(new XssEscapeServletFilter());
	 * filterRegistration.setOrder(1); filterRegistration.addUrlPatterns("/*");
	 * 
	 * return filterRegistration; }
	 */
	 
	@Bean
	public FilterRegistrationBean getFilterRegistrationBean()
	{
		FilterRegistrationBean registrationBean = new FilterRegistrationBean(new HelloFilter());
		// registrationBean.addUrlPatterns("/*"); // 서블릿 등록 빈 처럼 패턴을 지정해 줄 수 있다.
		registrationBean.setOrder(Integer.MAX_VALUE);
		return registrationBean;
	}
	
	private final ObjectMapper objectMapper;

	/*
	 * @Bean public MappingJackson2HttpMessageConverter jsonEscapeConverter() {
	 * ObjectMapper copy = objectMapper.copy();
	 * copy.getFactory().setCharacterEscapes(new HTMLCharacterEscapes()); return new
	 * MappingJackson2HttpMessageConverter(copy); }
	 */
	 
	
    @Autowired
    UserAuthCheckInterceptor userAuthCheckInterceptor;
	    
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(userAuthCheckInterceptor)
		.addPathPatterns("/*")
		.excludePathPatterns("/css/**","/fonts/**", "/plugin/**", "/scripts/**");
	}
}