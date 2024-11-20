package com.yuhan.TeamScheduleManagement.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class TestBoard {
	@Id
	@GeneratedValue
	private int seq;
	private String myName;
}
