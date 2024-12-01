package com.yuhan.TeamScheduleManagement.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yuhan.TeamScheduleManagement.domain.TeamSchedule;
import com.yuhan.TeamScheduleManagement.domain.TeamSchedule.ScheduleState;
import com.yuhan.TeamScheduleManagement.persistance.TeamScheduleRepository;

@SpringBootTest
public class TeamScheduleRepositoryTest {

	@Autowired
	private TeamScheduleRepository teamScheduleRepo;
	
	// 일정 등록 기능 단위 테스트
	@Test
	public void insertTeamSchedule() {
		TeamSchedule teamSchedule = new TeamSchedule();
		teamSchedule.setTeamScheduleMemberId("SeongJun");
		teamSchedule.setTeamNum(999);
		teamSchedule.setTeamScheduleStartDate("0000-00-00");
		teamSchedule.setTeamScheduleEndDate("9999-99-99");
		teamSchedule.setTeamScheduleState(ScheduleState.VACATION);
		teamScheduleRepo.save(teamSchedule);
	}
}
