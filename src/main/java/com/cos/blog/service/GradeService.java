package com.cos.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Grade;
import com.cos.blog.repository.GradeRepository;

//스프링이 컴포넌트 스캔으로 Bean에 등록해줌
@Service
public class GradeService {

	@Autowired
	private GradeRepository gradeRepository;
	
	@Transactional(readOnly = true)
	public List<Grade> 등급목록() {
		return gradeRepository.findAll();
	}
}
