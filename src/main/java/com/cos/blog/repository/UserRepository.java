package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

// DAO
// 자동으로 Bean 등록이 된다.
// @Repository 를 생략 가능하다.
//User 객체를 핸들링하며 Integer 키 값을 이용한다. (CRUD 용 키 값)
public interface UserRepository extends JpaRepository<User, Integer>{
	//SELECT * FROM userWHERE username = ?
	Optional<User> findByUsername(String username);
	Page<User> findByUsernameContaining(String keyword, Pageable pageable);
	Page<User> findByNickNameContaining(String keyword, Pageable pageable);
}
//JPA Naming 전략 (명시 된 이름으로 쿼리를 짠다)
//본래 함수명이 findByUserNameAndPassword 이겠지만 대문자마다 
//쿼리의 단어를 구분하므로 findByUsernameAndPassword 로 작성한다.
//SELECT * FROM user WHERE username = ? AND password = ?;
//User findByUsernameAndPassword(String username, String password);
/*
 * @Query(value="SELECT * FROM user WHERE username = ? AND password = ?"
 * ,nativeQuery = true) User login(String username, String password);
 */