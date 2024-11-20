package com.yuhan.TeamScheduleManagement.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yuhan.TeamScheduleManagement.domain.Team;

public interface TeamRepository extends JpaRepository<Team, Integer> {
	
}
