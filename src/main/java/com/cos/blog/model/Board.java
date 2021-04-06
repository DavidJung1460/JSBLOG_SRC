package com.cos.blog.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
//@Table(name = "board")
public class Board implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false,length = 100)
	private String title;

	@Lob
	private String contents;
	
	private int count;
	
	@ManyToOne(fetch = FetchType.EAGER) //Many = board / User = One . EAGER 무조건  들고와라 정책
	@JoinColumn(name="userid") //받아온 객체의 대상 테이블의 기본키를 기준으로 컬럼이 자동으로 만들어진다.
	private User user;//DB는 오브젝트를 저장할 수 없어서 FK 사용, 자바는 오브젝트를 저장할 수 있다.
	
	//board 셀렉트 시 board랑 reply를 알아서 조인하여 reply에 리스트 값만 넣어 달라 하는 변수 설정
	@OneToMany(mappedBy = "board",fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)//mappedBy:연관관계의 주인이 아니다. (난 FK가 아니다.), DB에 FK 만들지 말라는 옵션.
	//@JsonIgnoreProperties({"board"}) //reply 내부 board get 호출 못하게 막음으로써 무한참조방지
	@OrderBy("id desc")
	private List<Reply> replys;
	
	@CreationTimestamp
	private LocalDateTime regDate;
}


