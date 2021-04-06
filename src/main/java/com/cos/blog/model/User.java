package com.cos.blog.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.cos.blog.enumtype.RoleType;

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
	
	@Column(nullable = false,length = 50)
	private String email;
	
	//@ColumnDefault("'user'")
	@Column(length = 50)
	//DB는 RoleType이라는게 없다. 따라서 String 이라고 알려줘야한다.
	@Enumerated(EnumType.STRING)
	private RoleType role; //Enum을 쓰는게 좋다. / 권한 : admin, user, manager 명시 된 타입으로 강제됨
	
	@Column(length = 50)
	private String oauth;
	
	@CreationTimestamp //시간 자동 입력
	private Timestamp createDate;
	

}
