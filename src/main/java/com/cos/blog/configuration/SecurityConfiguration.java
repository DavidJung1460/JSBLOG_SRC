package com.cos.blog.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog.configuration.auth.PrincipalDetailService;

//Bean 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것 (아래 세 개는 Set 이다)
@Configuration //bean 등록 (IOC 관리)
@EnableWebSecurity //시큐리티 필터가 등록이 된다.
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소로 접근시 권한 및 인증을 미리 체크하겠다는 설정.
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}

	@Bean //IOC 등록 - 함수가 리턴하는 값을 스프링이 관리한다.
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	//시큐리티가 대신 로그인해주는데 Password를 가로채기 하는데
	//해당 Password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
	//같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있음.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD()); 
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			.csrf().disable() //csrf 토큰 비활성화 테스트시 적용시키는 옵션
			.authorizeRequests()
				.antMatchers("/","/auth/**","/assets/**","/js/**","/css/**","/images/**","/files/**")
				.permitAll()
				.anyRequest()
				.authenticated()
			.and()
				.formLogin()
				.loginPage("/auth/loginForm")
				//아래 주소로 접속 시 PrincipalDetailService의 loadUserByUsername 으로 이동한다.
				.loginProcessingUrl("/auth/loginProc")//스프링 시큐리티가 해당 주소의 로그인을 가로채서 대신 로그인해준다.
				.defaultSuccessUrl("/securityCallback"); //성공시 이동 url
	}
}
