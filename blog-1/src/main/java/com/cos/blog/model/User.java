package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴
@Entity // Table 생성
// @DynamicInsert // insert 시 null 제외 
public class User {

	@Id // Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 연결된  DB 전략에 따라 넘김
	private long id;
	
	@Column(nullable = false, length = 30, unique = true)
	private String username;
	
	@Column(nullable = false, length = 100)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	//@ColumnDefault("user")
	@Enumerated(EnumType.STRING)
	private RoleType role; // 권한 설정
	
	@CreationTimestamp // 시간 자동 입력
	private Timestamp createDate;
	
}
