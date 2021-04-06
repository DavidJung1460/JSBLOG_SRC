package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.enumtype.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

//스프링이 컴포넌트 스캔으로 Bean에 등록해줌
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional //Save 전체가 하나의 트랜잭션으로 묶임
	public void 회원가입(User user) {
		String rawPassword = user.getPassword(); //원문
		String encPassword = encoder.encode(rawPassword); //해쉬화
		
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}
	
	//영속화 인스턴스를 이용한 더티체킹 업데이트
	@Transactional
	public void 회원수정(User reqUser) {
		User user = userRepository.findById(reqUser.getId())
				.orElseThrow(()->{
					return new IllegalArgumentException("회원 검색 실패 : 회원을 찾을 수 없습니다.");
				});
		
		//회원 수정 시 Oauth 의 값에 따라서 지정이 되어 있을 경우 패스워드 변경 못하도록 제한
		//application.yml에 지정된 cos라는 패스워드만 사용하고 메일 변경 못하도록 제한
		if(user.getOauth() == null || user.getOauth().equals("")) {
			String rawPassword = reqUser.getPassword(); //원문
			String encPassword = encoder.encode(rawPassword); //해쉬화
			user.setPassword(encPassword);
			user.setEmail(reqUser.getEmail());
		}
	}

	@Transactional(readOnly = true)
	public User 회원찾기(String username) {
		User user = userRepository.findByUsername(username).orElseGet(()->{
			return new User();
		});
		return user;
	}
	
	/*
	 * @Transactional(readOnly = true)//select 할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성 유지)
	 * public User 로그인(User user) { return
	 * userRepository.findByUsernameAndPassword(user.getUsername(),
	 * user.getPassword()); }
	 */
}
