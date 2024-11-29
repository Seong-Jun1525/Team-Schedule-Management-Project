package com.yuhan.TeamScheduleManagement.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuhan.TeamScheduleManagement.domain.TeamSchedule;
import com.yuhan.TeamScheduleManagement.domain.TeamSchedule.ScheduleState;
import com.yuhan.TeamScheduleManagement.dto.TeamScheduleDTO;
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
    public void deleteSchedule(int scheduleId) {
        teamScheduleRepo.deleteById(scheduleId);
    }

    @Override
    public void updateSchedule(TeamSchedule teamSchedule) {
        if (teamScheduleRepo.existsById(teamSchedule.getTeamScheduleId())) {
            teamScheduleRepo.save(teamSchedule);
        }
    }

    @Override
    public TeamSchedule saveSchedule(TeamScheduleDTO dto, String memberId) {
    	// 날짜 포맷 지정
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	
        TeamSchedule schedule = new TeamSchedule();
        schedule.setTeamNum(dto.getTeamNum());
        schedule.setTeamScheduleMemberId(memberId);
        schedule.setTeamScheduleContent(dto.getTeamScheduleContent());
        
        String formattedDate = dto.getTeamScheduleStartDate().split("\\.")[0]; // 밀리초 제거
        schedule.setTeamScheduleStartDate(LocalDateTime.parse(formattedDate));

        String formattedEndDate = dto.getTeamScheduleEndDate().split("\\.")[0]; // 밀리초 제거
        schedule.setTeamScheduleEndDate(LocalDateTime.parse(formattedEndDate));

        
        schedule.setAllDay(dto.getAllDay());

        // ScheduleState 열거형 처리
        try {
            schedule.setTeamScheduleState(ScheduleState.valueOf(dto.getScheduleState().toUpperCase()));
        } catch (IllegalArgumentException e) {
            schedule.setTeamScheduleState(ScheduleState.ETC); // 기본값
        }

        return teamScheduleRepo.save(schedule);
    }

}