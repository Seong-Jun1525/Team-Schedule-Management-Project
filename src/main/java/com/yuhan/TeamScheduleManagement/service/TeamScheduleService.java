package com.yuhan.TeamScheduleManagement.service;

import java.util.List;

import com.yuhan.TeamScheduleManagement.domain.TeamSchedule;

public interface TeamScheduleService {

	List<TeamSchedule> getSchedulesByTeamNum(int teamNum);

	void deleteSchedule(int teamScheduleId);

	void updateSchedule(TeamSchedule teamSchedule);

	TeamSchedule insertTeamSchedule(TeamSchedule teamSchedule, String memberId);

	List<TeamSchedule> getScheduleByTeamNumAndTeamScheduleId(int teamNum, int teamScheduleId);

}