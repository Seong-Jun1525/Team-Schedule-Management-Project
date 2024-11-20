package com.yuhan.TeamScheduleManagement.service;

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
		// TODO Auto-generated method stub
		teamRepo.save(team);
	}

	@Override
	public void updateTeam(Team team) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Team getTeam(Team team) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
