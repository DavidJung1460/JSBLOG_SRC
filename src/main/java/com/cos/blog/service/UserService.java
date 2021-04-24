package com.cos.blog.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Grade;
import com.cos.blog.model.Search;
import com.cos.blog.model.UseAuth;
import com.cos.blog.model.User;
import com.cos.blog.repository.UseAuthRepository;
import com.cos.blog.repository.UserRepository;

//스프링이 컴포넌트 스캔으로 Bean에 등록해줌
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UseAuthRepository useAuthRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional //Save 전체가 하나의 트랜잭션으로 묶임
	public void 회원가입(User user) {
		String rawPassword = user.getPassword(); //원문
		String encPassword = encoder.encode(rawPassword); //해쉬화
		Grade grade = new Grade();
		grade.setGradeCd(2);
		
		user.setPassword(encPassword);
		user.setGrade(grade);
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
			if(reqUser.getPassword()==null ||reqUser.getPassword().equals("")) {
			} else {
				String rawPassword = reqUser.getPassword(); //원문
				String encPassword = encoder.encode(rawPassword); //해쉬화
				user.setPassword(encPassword);
			}
			user.setEmail(reqUser.getEmail());
		}
		user.setNickName(reqUser.getNickName());
	}

	@Transactional(readOnly = true)
	public User 회원찾기(String username) {
		User user = userRepository.findByUsername(username).orElseGet(()->{
			return new User();
		});
		return user;
	}

	public Page<User> 회원목록(Search search,Pageable pageable) {
		Page<User> user = null;
		if(search.getSearchText() != null) {
			if(search.getSearchSelect().equals("아이디")) {
				user = userRepository.findByUsernameContaining(search.getSearchText(),  pageable);
			}else {
				user = userRepository.findByNickNameContaining(search.getSearchText(),  pageable);
			}
		}else {
			user =  userRepository.findAll(pageable);
		}
		
		return user;
	}
	
	//영속화 인스턴스를 이용한 더티체킹 업데이트
		@Transactional
		public void 회원관리수정(int gradeCd,User reqUser) {
			User user = userRepository.findById(reqUser.getId())
					.orElseThrow(()->{
						return new IllegalArgumentException("회원 검색 실패 : 회원을 찾을 수 없습니다.");
					});
			
			//회원 수정 시 Oauth 의 값에 따라서 지정이 되어 있을 경우 패스워드 변경 못하도록 제한
			//application.yml에 지정된 cos라는 패스워드만 사용하고 메일 변경 못하도록 제한
			if(user.getOauth() == null || user.getOauth().equals("")) {
				if(reqUser.getPassword()==null ||reqUser.getPassword().equals("")) {
				} else {
					String rawPassword = reqUser.getPassword(); //원문
					String encPassword = encoder.encode(rawPassword); //해쉬화
					user.setPassword(encPassword);
				}
				user.setEmail(reqUser.getEmail());
			}
			Grade grade = new Grade();
			grade.setGradeCd(gradeCd);
			user.setNickName(reqUser.getNickName());
			user.setGrade(grade);
		}
		
		@Transactional
		public void 사용권한관리(UseAuth reqUseAuth) {
			UseAuth useAuth = useAuthRepository.findByUserAndAuthDelYn(reqUseAuth.getUser(),"N");
			if(useAuth == null) {
				
				User user = userRepository.findById(reqUseAuth.getUser().getId())
						.orElseThrow(()->{
							return new IllegalArgumentException("회원 검색 실패 : 회원을 찾을 수 없습니다.");
						});
				//if(user.getGrade().getGradeCd() > 2 ) {
					useAuthRepository.save(reqUseAuth);
				//}
			} else {
				useAuth.setGradeStartDate(reqUseAuth.getGradeStartDate());
				useAuth.setGradeExDate(reqUseAuth.getGradeExDate());
				useAuth.setAuthPauseYn(reqUseAuth.getAuthPauseYn());
				useAuth.setAuthDelYn(reqUseAuth.getAuthDelYn());
			}
		}
		
		@Transactional
		public UseAuth 사용권한조회(User reqUser) {
			UseAuth useAuth = useAuthRepository.findByUserAndAuthDelYn(reqUser,"N");
			return useAuth;
		}
		
		@Transactional
		public void 사용권한확인및갱신(User User) {
			
		}
	
	/*
	 * @Transactional(readOnly = true)//select 할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성 유지)
	 * public User 로그인(User user) { return
	 * userRepository.findByUsernameAndPassword(user.getUsername(),
	 * user.getPassword()); }
	 */
}
