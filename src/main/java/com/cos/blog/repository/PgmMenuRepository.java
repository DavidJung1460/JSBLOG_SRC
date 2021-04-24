package com.cos.blog.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.Grade;
import com.cos.blog.model.PgmMenu;

public interface PgmMenuRepository extends JpaRepository<PgmMenu, Grade> {

	List<PgmMenu> findAllByGradeLessThanEqual(Grade grade,Sort sort);
	List<PgmMenu> findAllByGradeBetween(Grade fromGrade,Grade toGrade,Sort sort);
}