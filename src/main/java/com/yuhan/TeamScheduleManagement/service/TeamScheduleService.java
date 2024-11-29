package com.yuhan.TeamScheduleManagement.service;

import java.util.List;
import java.util.Map;

import com.yuhan.TeamScheduleManagement.domain.TeamSchedule;
import com.yuhan.TeamScheduleManagement.dto.TeamScheduleDTO;

public interface TeamScheduleService {

	List<TeamSchedule> getSchedulesByTeamNum(int teamNum);

	void deleteSchedule(int scheduleId);

	void updateSchedule(TeamSchedule teamSchedule);

	TeamSchedule saveSchedule(TeamScheduleDTO dto, String memberId);

}
