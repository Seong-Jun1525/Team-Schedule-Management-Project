package com.yuhan.TeamScheduleManagement.service;

import com.yuhan.TeamScheduleManagement.domain.Team;

public interface TeamService {
	public void insertTeam(Team team);
	public void updateTeam(Team team);
	public Team getTeam(Team team);
	public Team getTeam(String userId);
	public Team getTeamByTeamNum(int teamNum);
}
