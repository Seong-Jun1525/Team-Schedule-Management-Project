package com.yuhan.TeamScheduleManagement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuhan.TeamScheduleManagement.domain.Team;
import com.yuhan.TeamScheduleManagement.domain.Team.TeamState;
import com.yuhan.TeamScheduleManagement.persistance.TeamRepository;

@Service
public class TeamServiceImpl implements TeamService {
	@Autowired
	private TeamRepository teamRepo;

	@Override
	public void insertTeam(Team team) {
		// TODO 팀 등록 기능
		teamRepo.save(team);
	}

	@Override
	public void updateTeam(Team team) {
		// TODO 팀 종료 기능
		Team findTeam = teamRepo.findById(team.getTeamNum()).get();
		findTeam.setTeamState(Team.TeamState.INACTIVE);
		teamRepo.save(findTeam);
	}

	@Override
	public Team getTeam(Team team) {
		// TODO 추후 개발 예정
		return null;
	}

	@Override
	public Team getTeam(String userId) {
		// TODO userId로 등록된 팀 가져오기(상태가 1인 경우만)
		TeamState checkStateValue = TeamState.ACTIVE;
		Team teamInfo = teamRepo.findByMemberIdAndTeamState(userId, checkStateValue);
		System.out.println("teamInfo : " + teamInfo);
		return teamInfo;
	}

	@Override
	public Team getTeamByTeamNum(int teamNum) {
		return teamRepo.getTeamByTeamNum(teamNum);
	}
}
