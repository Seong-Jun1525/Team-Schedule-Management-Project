package com.yuhan.TeamScheduleManagement.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yuhan.TeamScheduleManagement.domain.Team;
import com.yuhan.TeamScheduleManagement.domain.Team.TeamState;

public interface TeamRepository extends JpaRepository<Team, Integer> {

	Team findByMemberId(String userId);
	Team findByMemberIdAndTeamState(String userId, TeamState checkStateValue);
	Team getTeamByTeamNum(int teamNum);
	boolean existsByMemberIdAndTeamPosition(String memberId, Team.TeamPosition teamPosition);
	
}
