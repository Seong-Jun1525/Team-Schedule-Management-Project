package com.yuhan.TeamScheduleManagement.repository;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yuhan.TeamScheduleManagement.domain.Team;
import com.yuhan.TeamScheduleManagement.domain.Team.TeamPosition;
import com.yuhan.TeamScheduleManagement.persistance.TeamRepository;

@SpringBootTest
public class TeamRepositoryTest {

	@Autowired
	private TeamRepository teamRepo;
	
	// team 등록 단위테스트
	// @Test
	public void testInsertTeam() {
		Team team = new Team();
		team.setTeamName("DoubleSJ Test");
		team.setMemberId("SeongJun");
		team.setTeamPosition(TeamPosition.LEADER);
		team.setTeamRole("PM");
		teamRepo.save(team);
	}
	
	// team 상태 업데이트 단위테스트
	// @Test
	public void testUpdateTeam() {
		Optional<Team> findTeam = teamRepo.findById(1);
		Team team = findTeam.get();
		team.setTeamState(Team.TeamState.INACTIVE);
	}
}
