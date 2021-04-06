package com.cos.blog.test.controller;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.enumtype.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

//html 파일이 아니라 data 를 return 해주는 controller
//JPA 영속성 컨텍스트를 이용한 CRUD 컨트롤러
@RestController
public class DummyControllerTest {
	//@Autowired는 스프링에 UserRepository 형식으로 메모리상 관리되는 타입을 넣어줘라(매핑)
	//의존성 주입 (DI)
	@Autowired
	private UserRepository userRepository;
	
	//{id} 주소로 파라메터를 전달 받을 수 있음
	//http://localhost:8000/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		//null 이 무조건 없을 경우 optionall 에서 제공하는 get이용
		//User user = userRepository.findById(id).get();
		
		//null 일 경우 빈 객체 반환 리스트 반환 시 유용 (값이 없을 경우 빈객체)
		/*
		 * User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
		 * 
		 * @Override public User get() { // TODO Auto-generated method stub return new
		 * return new User(); } });
		 */
		 
		
		//exceltion 을 반환하는 문법이나 실제로 값이 무조건 나와야하는 경우 사용하는 것이 좋다.

		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {

			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
			}
		}); 
		
		//람다식 - 위의 중간 문법을 생략 가능하다.
		/*
		 * User user = userRepository.findById(id).orElseThrow(()-> { return new
		 * IllegalArgumentException("해당 사용자는 없습니다."); });
		 */		 
		
		
		//요청 : 웹브라우져 
		//User 객체 = 자바 오브젝트
		//변환 (웹 브라우저가 이해할 수 있는 데이터) -> json
		//스프링 부트 = MessageConverter 라는 애가 응답시에 자동 작동
		//만약에 자바 오브젝트 리턴 시 MessageConverter가 Jackson 라이브러리를 호출해서
		//user 오브젝트를 json으로 변환해서 브라우져에 던져준다.
		return user;
	}
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			// TODO: handle exception
			return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
		}
		
		return "삭제되었습니다. id:"+id;
	}
	
	//email,password 수정 예정
	//save 함수를 통한 업데이트 방법 (해당 데이터를 받아서 특정 데이터 셋 후 변경)
	//save 함수는 id를 전달하지 않으면 insert 해줌
	//save 함수는 id를 전달하면 update 해줌
	//save 함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert 진행
	@Transactional//함수가 종료되면 (return) 트랜잭션 종료되며 자동 commit이 됨. 
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id,@RequestBody User requestUser) {//json 데이터 >java Object로 받아줌 (MessageConverter의 Jackson 라이브러리가 변환해서 받아줌) requestBody
		System.out.println("id : "+id);
		System.out.println("password : "+requestUser.getPassword());
		System.err.println("email : "+requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		//requestUser.setId(id);
		//requestUser.setUsername("ssar");
		
		//원래 save는 inser함수이다.
		//하지만 키값이 디비에 존재하면 update 해준다.
		//하지만 받아오지 못한 데이터에 해당 되는 컬럼은 null이된다. 그러므로 save는 update에 안쓴다.
		//userRepository.save(user);
		
		//더티 체킹 상단 transactional 어노테이션 관련 save없이 update시킬때 사용함.
		//JPA영속성 컨텍스트에 user객체가 쌓인다.(영속화)
		//영속화 된 user객체를 DB에 flush(임시 저장소에 있는 정보를 메인 저장소로 옮기는 것/buffer를 비운다) 한다.
		//상단의 user 객체 조회한것(select)로 jpa 영속성 컨텍스트에 해당 로우 데이터를 영속화 시키고
		//영속화 된 데이터의 값에 user 데이터를 변경하여서 DB와 영속화로 연결된 USER데이터가 update된다.
		
		return null;
	}
	
	
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	// 한페이지당 2건에 데이터를 리턴 받아 볼 예정
	@GetMapping("/dummy/user") //Direction 앞에 sort 가 생략되었으므로 나중에 확인
	public List<User> pageList(@PageableDefault(size=2,sort="id",direction =  Direction.DESC) Pageable pageable){
		Page<User> pagingUser = userRepository.findAll(pageable); //page 형의 리스트외 필요 데이터까지 리턴
		
		if(pagingUser.isLast()) {
			//마지막 데이터에 관련 된 분기 예시
		}
		
		List<User> users = pagingUser.getContent(); //리스트 데이터만 리턴
		return users;
	}
	
	
	//http://localhost:8000/blog/dummy/join(요청)
	//http의 body에 username,password,email 데이터를 가지고(요청)
	@PostMapping("/dummy/join")
	public String join(User user) {//key=value (약속 된 규칙)
		System.out.println("username : "+user.getUsername());
		System.out.println("password : "+user.getPassword());
		System.out.println("email : "+user.getEmail());
		
		//RoleType enum에 정해진 값 중 골라서 넣으면 user model에 명시 된 변수 타입으로 데이터 삽입
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
}
