package com.cos.blog.mvc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Getter
//@Setter
@Data // getter,setter 둘 다 포함
@AllArgsConstructor //모든 페이지에서 쓸수있는 생성자 포함
//@RequiredArgsConstructor
@NoArgsConstructor
public class Member {
	//final 형식으로 불변성 부여
	private  int id;
	private  String username;
	private  String password;
	private  String email;
	
	//@Builder
	/*public Member(int id, String username, String password, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}*/
	
	
}
