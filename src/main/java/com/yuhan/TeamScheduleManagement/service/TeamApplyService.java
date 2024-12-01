package com.yuhan.TeamScheduleManagement.service;

import java.util.List;

import com.yuhan.TeamScheduleManagement.domain.TeamApply;

public interface TeamApplyService {
    boolean applyForTeam(TeamApply teamApply);
    boolean isAlreadyApplied(String applier, String projectLeader);
    boolean isTeamLeader(String userId);
    List<TeamApply> getApplicationsForLeader(String projectLeader);
    boolean acceptApplication(String applier, int teamNum); // 신청 수락
    boolean rejectApplication(String applier); // 신청 거절
    
}
