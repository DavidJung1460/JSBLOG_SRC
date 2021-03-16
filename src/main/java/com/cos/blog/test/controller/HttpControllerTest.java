package com.cos.blog.test.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.mvc.domain.Member;

//사용자 요청에 대한 데이터 응답 (Controller)
@RestController
public class HttpControllerTest {
	private static final String TAG = "httpControllerTest: "; 
	@GetMapping("/http/lombok")
	public String lombokTest() {
			Member m = new Member(1,"ggg","11234","email");
			System.out.println(TAG+"getter"+m.getId());
			m.setId(2);
			System.out.println(TAG+"getter"+m.getId());
			return "lombok test 완료";
	}
	//http://localhost:8080/http/get (select)
	@GetMapping("/http/get")
	public String getTest(Member m) {
		return "get 요청 : "+m.getId()+" / "+m.getUsername()+ " / "+m.getEmail();
	}
	
	//http://localhost:8080/http/post (insert)
	/*
	 * @PostMapping("/http/post") public String postTest(Member m) { return
	 * "post 요청 : "+m.getId()+" / "+m.getUsername()+ " / "+m.getEmail(); }
	 */
	@PostMapping("/http/post")//text/plain(평문) / application/json
	public String postTest(@RequestBody Member m) { //text로 받으면 평문 & 객체로 받으면 / json 매핑 가능 *MessageConverter(스프링부트)가 자동 매핑 해줌
		return "post 요청 : "+m.getId()+" / "+m.getUsername()+ " / "+m.getEmail();
	}	
	//http://localhost:8080/http/put (update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청 : "+m.getId()+" / "+m.getUsername()+ " / "+m.getPassword();
	}
	
	//http://localhost:8080/http/delete (delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
	
}
