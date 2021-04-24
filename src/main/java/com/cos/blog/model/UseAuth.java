package com.cos.blog.model;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@Entity // 클래스가 자동으로 MySQL에 테이블이 생성이 된다.
@Builder
//@Table(name = "useauth")
//@DynamicInsert //null 인 컬럼은 insert시 제외
public class UseAuth implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id //primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private  int authId; //시퀀스, auto_increment 용 변수	
	
	@ManyToOne(fetch = FetchType.EAGER) //Many = board / User = One . EAGER 무조건  들고와라 정책
	@JoinColumn(name="id") //받아온 객체의 대상 테이블의 기본키를 기준으로 컬럼이 자동으로 만들어진다.
	private User user;//DB는 오브젝트를 저장할 수 없어서 FK 사용, 자바는 오브젝트를 저장할 수 있다.
	
	@DateTimeFormat(pattern = "yyyy-MM-dd kk:mm:ss")
	private LocalDateTime gradeStartDate; //권한시작일자

	@DateTimeFormat(pattern = "yyyy-MM-dd kk:mm:ss")
	private LocalDateTime gradeExDate; //권한만료일자
	
	@Column(length=1)
	private String authPauseYn; //권한중지여부
	
	private LocalDateTime authPauseDate; //권한중지일자
	
	@Column(length=1)
	private String authDelYn; //권한삭제여부
	
	@DateTimeFormat(pattern = "yyyy-MM-dd kk:mm:ss")
	@CreationTimestamp //생성시 시간 자동 입력
	private LocalDateTime payDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd kk:mm:ss")
	@UpdateTimestamp//업데이트시 시간 자동 입력
	private LocalDateTime updDate;
	
	
}
