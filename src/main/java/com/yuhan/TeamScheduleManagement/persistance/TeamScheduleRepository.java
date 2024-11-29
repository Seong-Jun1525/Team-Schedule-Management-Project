package com.yuhan.TeamScheduleManagement.persistance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yuhan.TeamScheduleManagement.domain.TeamSchedule;

public interface TeamScheduleRepository extends JpaRepository<TeamSchedule, Integer>{

	List<TeamSchedule> findByTeamNum(int teamNum);

}
