package com.cos.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.Board;
import com.cos.blog.model.PgmMenu;
import com.cos.blog.model.User;

// DAO
// 자동으로 Bean 등록이 된다.
// @Repository 를 생략 가능하다.
public interface BoardRepository extends JpaRepository<Board, Integer>{

	Page<Board> findBytitleContainingAndNoticeYnAndPgmMenu(String searchText, String noticeYn, PgmMenu pgmMenu,Pageable pageable);

	Page<Board> findByUserContainingAndNoticeYnAndPgmMenu(User user, String noticeYn, PgmMenu pgmMenu, Pageable pageable);

	//@Query(value="SELECT * FROM user WHERE username = ? AND password = ?",nativeQuery = true)
	@Query(value="SELECT * FROM board a, (SELECT * FROM user WHERE nickName LIKE ? ) b WHERE a.userid = b.id AND noticeYn = ? AND pgmId = ?",nativeQuery = true)
	Page<Board> findByNicknameAndPgmMenu(String nickname, String noticeYn, PgmMenu pgmMenu,Pageable pageable);

	Page<Board> findByNoticeYnAndPgmMenu(String string, PgmMenu pgmMenu,Pageable pageable);

	Page<Board> findByMainNoticeYn(String string, Pageable pageable);
}
