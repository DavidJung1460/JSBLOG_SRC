package com.cos.blog.mvc.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cos.blog.mvc.domain.Board;

/**
 * 게시판 Repository interface
 * @author 정희진
 */
@Repository
public interface BoardRepositoryMybatis {
	
	List<Board> getList();
	
	Board get(int boardSeq);
	
	void save(Board board);
	
	void update(Board board);
	
	void delete(int boardSeq);
}
