package com.yuhan.TeamScheduleManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuhan.TeamScheduleManagement.domain.Team.TeamPosition;
import com.yuhan.TeamScheduleManagement.domain.TeamApply;
import com.yuhan.TeamScheduleManagement.domain.User;
import com.yuhan.TeamScheduleManagement.persistance.TeamApplyRepository;
import com.yuhan.TeamScheduleManagement.persistance.TeamRepository;
import com.yuhan.TeamScheduleManagement.persistance.UserRepository;

@Service
public class TeamApplyServiceImpl implements TeamApplyService{

    @Autowired
    private TeamApplyRepository teamApplyRepository;
    
    @Autowired
    private TeamRepository teamRepository;
    
    @Autowired
    private UserRepository userRepository;

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
    
    @Override
    public boolean isTeamLeader(String userId) {
        // 팀 테이블에서 리더(memberId)로 등록되어 있는지 확인
        return teamRepository.existsByMemberIdAndTeamPosition(userId, TeamPosition.LEADER);
    }

    @Override
    public List<TeamApply> getApplicationsForLeader(String projectLeader) {
        // 팀 신청 테이블에서 리더에 해당하는 신청 목록 조회
        return teamApplyRepository.findAllByProjectLeader(projectLeader);
    }
    
    @Override
    public boolean acceptApplication(String applier, int teamNum) {
        try {
            // 1. User 테이블에서 해당 신청자의 teamId 업데이트
            Optional<User> userOptional = userRepository.findById(applier);
            if (userOptional.isEmpty()) {
                return false; // 신청자가 존재하지 않음
            }

            User user = userOptional.get();
            user.setTeamId(teamNum); // 팀 번호 업데이트
            userRepository.save(user);

            // 2. TeamApply 테이블에서 해당 신청 삭제
            teamApplyRepository.deleteByApplier(applier);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean rejectApplication(String applier) {
        try {
            // TeamApply 테이블에서 해당 신청 삭제
            teamApplyRepository.deleteByApplier(applier);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
