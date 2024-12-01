package com.yuhan.TeamScheduleManagement.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "team_apply")
public class TeamApply {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int teamApplyNum;
	
	@Column(nullable = false, length = 20)
	private String applier;
	
	@Column(nullable = false, length = 20)
	private String projectLeader;
}
