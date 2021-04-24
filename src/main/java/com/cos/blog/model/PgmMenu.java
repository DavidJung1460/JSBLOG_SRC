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
import javax.persistence.OrderBy;

import org.hibernate.annotations.CreationTimestamp;
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
//@Table(name = "pgmmenu")
public class PgmMenu implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pgmCd;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="gradecd")
	private Grade grade;
	
	@Column(nullable=false,length = 100)
	private String pgmName;
	
	@Column(nullable=false,length = 200)
	private String pgmUrl;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@OrderBy("pgmLank asc")
	private int pgmLank;
	
	@Column(nullable=false,length = 1)
	private String leftMenuYn;
	
	@Column(nullable=false,length = 1)
	private String userUseYn;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd kk:mm:ss")
	@CreationTimestamp
	private LocalDateTime regDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd kk:mm:ss")
	@CreationTimestamp
	private LocalDateTime updDate;
}


