package com.cos.blog.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.Grade;
import com.cos.blog.model.PgmMenu;

public interface PgmMenuVoRepository extends JpaRepository<PgmMenu, Integer> {

}
