package com.cos.blog.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
//@Table(name = "blacklist")
public class BlackList implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id //primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private  int blackId; //시퀀스, auto_increment 용 변수
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="username")
	private User userName;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="ip")
	private User userIp;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd kk:mm:ss")
	@CreationTimestamp
	private LocalDateTime regDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd kk:mm:ss")
	@UpdateTimestamp
	private LocalDateTime updDate;
}


