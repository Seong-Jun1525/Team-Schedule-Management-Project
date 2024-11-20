package com.yuhan.TeamScheduleManagement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuhan.TeamScheduleManagement.domain.Team;
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
	
}
