package com.yuhan.TeamScheduleManagement.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "team_schedule")
public class TeamSchedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int teamScheduleId;
	
	@Column(nullable = false)
	private int teamNum;
	
	@Column(nullable = false, length = 20)
	private String teamScheduleMemberId;
	
	@Column(nullable = false, length = 30)
	private String teamScheduleStartDate;
	
	@Column(nullable = false, length = 30)
	private String teamScheduleEndDate;
	
	@Column(nullable = false)
	private Boolean allDay = true;

    @Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
    private ScheduleState teamScheduleState;

    @Column(updatable = true)
    private LocalDateTime createdAt = LocalDateTime.now();
    
    public enum ScheduleState {
    	REGULAR_MEETING, ADDITIONAL_MEETING, VACATION, ETC
    }
}