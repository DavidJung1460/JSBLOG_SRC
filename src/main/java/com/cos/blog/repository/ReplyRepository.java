package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {
	
	//객체로 받을수 없기 때문에 각각 순서에 맞추어 파라미터 형식으로 전달받아야 한다.
	//따라서 해당 부분은 네이티브 쿼리로 진행하는 것보다 mybatis로 진행하는 것이 유지관리에 용이할 수 있겠다.
	@Modifying
	@Query(value="INSERT INTO reply(userId, boardId, content, createDate) VALUES(?1,?2,?3,now())",nativeQuery = true)
	int mSave(int userId, int boardId, String content);
}
