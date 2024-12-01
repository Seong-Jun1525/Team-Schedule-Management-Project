package com.yuhan.TeamScheduleManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuhan.TeamScheduleManagement.domain.TeamSchedule;
import com.yuhan.TeamScheduleManagement.persistance.TeamScheduleRepository;

@Service
public class TeamScheduleServiceImpl implements TeamScheduleService {

    @Autowired
    private TeamScheduleRepository teamScheduleRepo;

    @Override
    public List<TeamSchedule> getSchedulesByTeamNum(int teamNum) {
        return teamScheduleRepo.findByTeamNum(teamNum);
    }

    @Override
    public void deleteSchedule(int teamScheduleId) {
        teamScheduleRepo.deleteById(teamScheduleId);
    }

    @Override
    public void updateSchedule(TeamSchedule teamSchedule) {
        if (teamScheduleRepo.existsById(teamSchedule.getTeamScheduleId())) {
            teamScheduleRepo.save(teamSchedule);
        }
    }

    // 일정 등록
	@Override
	public TeamSchedule insertTeamSchedule(TeamSchedule teamSchedule, String memberId) {
		System.out.println("teamScheduleServiceImpl : " + teamSchedule);
		teamSchedule.setTeamScheduleMemberId(memberId);
		return teamScheduleRepo.save(teamSchedule);
	}

	@Override
	public List<TeamSchedule> getScheduleByTeamNumAndTeamScheduleId(int teamNum, int teamScheduleId) {
		
		return teamScheduleRepo.findByTeamNumAndTeamScheduleId(teamNum, teamScheduleId);
	}

}