package com.cos.blog.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

@Entity //User 클래스가 자동으로 MySQL에 테이블이 생성이 된다.
@Table(name = "BLOGUSER")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id //primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private  int id; //시퀀스, auto_increment 용 변수
	
	@Column(nullable = false,length = 30)
	private String username; //아이디
	
	@Column(nullable = false,length = 100) //123456 => 해쉬(비밀번호 암호화 대상)
	private String password;
	
	@Column(nullable = false,length = 50)
	private String email;
	
	@ColumnDefault("'user'")
	@Column(nullable = false,length = 50)
	private String role; //Enum을 쓰는게 좋다. / 권한 : admin, user, manager
	
	@CreationTimestamp //시간 자동 입력
	private Timestamp createDate;
	
	
}
