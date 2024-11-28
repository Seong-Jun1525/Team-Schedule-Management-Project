package com.yuhan.TeamScheduleManagement.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "team_board")
public class TeamBoard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int teamBoardId;
	
	@Column(nullable = false, length = 50)
	private String teamBoardTitle;

	@Column(nullable = false, length = 20)
	private String teamBoardMemberId;
	
	@Column(nullable = false, length = 20)
	private String teamBoardWriter;
	
	@Column(nullable = false, length = 255)
	private String teamBoardContent;
	
	@Column(nullable = false, updatable = true)
    private LocalDateTime createdAt;
	
	@PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
	
}
