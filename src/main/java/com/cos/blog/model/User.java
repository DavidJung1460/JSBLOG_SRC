package com.cos.blog.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // getter,setter 둘 다 포함
@AllArgsConstructor //모든 페이지에서 쓸수있는 생성자 포함
//@RequiredArgsConstructor
@NoArgsConstructor
@Entity //User 클래스가 자동으로 MySQL에 테이블이 생성이 된다.
@Builder
//@Table(name = "user")
//@DynamicInsert //null 인 컬럼은 insert시 제외
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id //primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private  int id; //시퀀스, auto_increment 용 변수
	
	@Column(nullable = false,length = 100,unique = true)
	private String username; //아이디
	
	@Column(nullable = false,length = 100) //123456 => 해쉬(비밀번호 암호화 대상)
	private String password;
	
	@Column(nullable = false,length = 100) //123456 => 해쉬(비밀번호 암호화 대상)
	private String nickName;
	
	@Column(length = 50)
	private String email;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="gradecd")
	private Grade grade;
	
	@Column(length = 50)
	private String oauth;
	
	@Column(length=20)
	private String ip;
	
	@Column(length=1)
	private String perInfoYn;
	
	@Column(length=1)
	private String promotionYn;
	
	@Column(length=1)
	private String termsServiceYn;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd kk:mm:ss")
	@CreationTimestamp //생성시 시간 자동 입력
	private Timestamp signUpDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd kk:mm:ss")
	@UpdateTimestamp//업데이트시 시간 자동 입력
	private Timestamp updDate;
}
