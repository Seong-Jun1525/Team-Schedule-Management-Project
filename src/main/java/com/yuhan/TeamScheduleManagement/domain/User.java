package com.yuhan.TeamScheduleManagement.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User {
	@Id // Primary Key 설정
	private String userId;
	
	@Column(nullable = false)
	private String userPw;
	
	@Column(nullable = false, length = 20)
	private String userName;
	
	@Column(nullable = false, length = 30)
	private String userEmail;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 10)
	private UserGender userGender;
	
	@Column(nullable = false, length = 50)
	private String userJob;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UserState userState;
	
	@Column(nullable = false ,updatable = false)
	private LocalDateTime dateSignedUp;
	
	@Column(nullable = true)
	private String teamId;
	
	@PrePersist
	protected void onCreate() {
		this.dateSignedUp = LocalDateTime.now();
		this.userState  = UserState.OFFLINE;
	}
	
	public enum UserGender{
		MAN, WOMAN, ANONYM
	}
	
	public enum UserState {
		OFFLINE, ONLINE;
	}
		
}
