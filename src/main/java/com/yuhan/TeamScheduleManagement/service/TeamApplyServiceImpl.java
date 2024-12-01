package com.yuhan.TeamScheduleManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuhan.TeamScheduleManagement.domain.TeamApply;
import com.yuhan.TeamScheduleManagement.persistance.TeamApplyRepository;

@Service
public class TeamApplyServiceImpl implements TeamApplyService{

    @Autowired
    private TeamApplyRepository teamApplyRepository;

    @Override
    public boolean applyForTeam(TeamApply teamApply) {
        try {
            teamApplyRepository.save(teamApply); // 팀 신청 정보 저장
            return true; // 저장 성공 시 true 반환
        } catch (Exception e) {
            e.printStackTrace();
            return false; // 저장 실패 시 false 반환
        }
    }

    @Override
    public boolean isAlreadyApplied(String applier, String projectLeader) {
        return teamApplyRepository.existsByApplierAndProjectLeader(applier, projectLeader);
    }

}
