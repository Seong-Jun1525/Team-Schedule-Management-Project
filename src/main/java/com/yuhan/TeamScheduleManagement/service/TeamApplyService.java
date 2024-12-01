package com.yuhan.TeamScheduleManagement.service;

import com.yuhan.TeamScheduleManagement.domain.TeamApply;

public interface TeamApplyService {
    boolean applyForTeam(TeamApply teamApply);
    boolean isAlreadyApplied(String applier, String projectLeader);
}
