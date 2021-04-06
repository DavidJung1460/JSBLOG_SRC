package com.cos.blog.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.cos.blog.model.KakaoProfile;
import com.cos.blog.model.OAuthToken;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//Spring Security 에서 /auth/ 주소를 가진 요청만 허용하였음
//주소가 / (index.jsp / index.html) 허용
//static 이하에 있는 /js/**, /css/**, /image/** 허용
@Controller
public class UserController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@Value("${cos.key}")
	private String cosKey;
	
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		
		return "um/joinForm";
	}
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		
		return "um/loginForm";
	}
	
	@GetMapping("/um/updateForm")
	public String updateForm() {
		
		return "um/userUpdateForm";
	}
	
	//responseBody 는 데이터를 리턴해주는 컨트롤러 함수임을 의미
	@GetMapping("/auth/kakao/callback")
	public String kakaoCallback(String code){
		//post 방식으로 key=value 데이터를 전송 요청(카카오쪽으로)
		//Retrofit2 / OkHttp / RestTemplate 값 전달하는 방법은 세 개 이상임.

		RestTemplate rt = new RestTemplate();
		
		//HttpHeader 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type","application/x-www-form-urlencoded;charset=utf-8");
		
		//HttpBody 오브젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
				
		params.add("grant_type", "authorization_code");
		params.add("client_id", "6e74fb94e1bd8860d3ec8ea3f54396d6");
		params.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
		params.add("code", code);
		
		//HttpHeader 와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = 
				new HttpEntity<>(params,headers);
		
		//Http 요청하기 - post방식으로 - 그리고 response 변수의 응답 받음.
		ResponseEntity<String> response = rt.exchange(
				"https://kauth.kakao.com/oauth/token",
				HttpMethod.POST,
				kakaoTokenRequest,
				String.class
		);	
		
		//Gson,Json Simple, ObjectMapper 등... 라이브러리중 아래 라이브러리 사용
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oauthToken = 	null;
		try {
			oauthToken = objectMapper.readValue(response.getBody(),OAuthToken.class);
		} catch(JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		System.out.println("카카오 엑세스 토큰 : "+oauthToken.getAccess_token());
		
		/*
		 * 요청주소 : http://kapi.kakao.com 헤더값 Authorization: Bearer {ACCESS_TOKEN}
		 * Content-type : application/x-www-form-urlencoded;charset=utf-8
		 */
		
		RestTemplate rt2 = new RestTemplate();
		
		//HttpHeader 오브젝트 생성
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization","Bearer "+oauthToken.getAccess_token());
		headers2.add("content-type","application/x-www-form-urlencoded;charset=utf-8");
		
		
		//HttpHeader 와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = 
				new HttpEntity<>(headers2);
		
		//Http 요청하기 - post방식으로 - 그리고 response 변수의 응답 받음.
		ResponseEntity<String> response2 = rt.exchange(
				"https://kapi.kakao.com/v2/user/me",
				HttpMethod.POST,
				kakaoProfileRequest,
				String.class
		);	
		
		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile = 	null;
		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(),KakaoProfile.class);
		} catch(JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		//User 오브젝트 : username, password, email (기본 객체 구성 시 필요 포인트)
		System.out.println("카카오 아이디(번호) : "+kakaoProfile.getId());
		System.out.println("카카오 이메일 : "+kakaoProfile.getKakao_account().getEmail());
		
		System.out.println("블로그 username : "+kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId());
		System.out.println("블로그 이메일 : "+kakaoProfile.getKakao_account().getEmail());
		
		System.out.println("블로그서버 패스워드 : "+cosKey);
		
		
		User kakaoUser = User.builder()
				.username(kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId())
				.password(cosKey)
				.email(kakaoProfile.getKakao_account().getEmail())
				.oauth("kakao")
				.build();
		
		//가입자 혹은 비가입자 체크해서 처리
		User originUser = userService.회원찾기(kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId());
		
		if(originUser.getUsername() == null) {
			System.out.println("해당 사용자는 기존 회원이 아니므로 회원가입을 진행합니다..");
			userService.회원가입(kakaoUser);
		}
		
		//기존 사용자 존재 여부 null이 아닐 경우와 추가 한 경우 로그인 처리
		Authentication authentication =
				new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), cosKey);
		
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(authentication);
		
		return "redirect:/";
	}
}
