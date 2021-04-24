package com.cos.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Grade;
import com.cos.blog.model.PgmMenu;
import com.cos.blog.repository.PgmMenuRepository;
import com.cos.blog.repository.PgmMenuVoRepository;
import com.cos.blog.repository.UseAuthRepository;

//스프링이 컴포넌트 스캔으로 Bean에 등록해줌
@Service
public class BlogService {
	
	@Autowired
	private PgmMenuRepository blogPgmMenuRepository;
	
	@Autowired
	private UseAuthRepository useAuthRepository;
	
	@Autowired
	private PgmMenuVoRepository blogPgmMenuVoRepository;
	
	@Transactional(readOnly = true)
	public List<PgmMenu> 기본좌측메뉴리스트(Grade grade) {
		
		List<PgmMenu>  left = blogPgmMenuRepository.findAllByGradeLessThanEqual(grade,sortByColumn("pgmLank"));
		return left;
	}
	
	@Transactional(readOnly = true)
	public List<PgmMenu> 좌측메뉴리스트(Grade toGrade) {
		Grade fromGrade = new Grade();
		fromGrade.setGradeCd(2);
		List<PgmMenu>  left = blogPgmMenuRepository.findAllByGradeBetween(fromGrade,toGrade,sortByColumn("pgmLank"));
		return left;
	}
	
	@Transactional(readOnly = true)
	public PgmMenu 프로그램메뉴조회(int pgmCd) {
		PgmMenu  pgmMenu = blogPgmMenuVoRepository.findById(pgmCd)
				.orElseThrow(()->{
					return new IllegalArgumentException("프로그램 찾기 실패 : 프로그램을 찾을 수 없습니다.");
				});
		
		return pgmMenu;
	}
	private Sort sortByColumn(String column) {
	    return Sort.by(Sort.Direction.ASC, column);
	}

}

