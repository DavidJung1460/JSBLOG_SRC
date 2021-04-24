package com.cos.blog.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.cos.blog.enumtype.RoleType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
//@Table(name = "grade")
public class Grade implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int gradeCd;
	
	//DB는 RoleType이라는게 없다. 따라서 String 이라고 알려줘야한다.
	//@ColumnDefault("'user'")
	@Column(nullable=false,length = 50)
	@Enumerated(EnumType.STRING)
	private RoleType gradeName;//Enum을 쓰는게 좋다. / 권한 : admin, user, manager 명시 된 타입으로 강제됨
	
	@DateTimeFormat(pattern = "yyyy-MM-dd kk:mm:ss")
	@CreationTimestamp
	private LocalDateTime regDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd kk:mm:ss")
	@UpdateTimestamp
	private LocalDateTime updDate;
}


