package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.Grade;

public interface GradeRepository extends JpaRepository<Grade, Integer> {
	
}
