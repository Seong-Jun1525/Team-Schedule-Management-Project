package com.yuhan.TeamScheduleManagement.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yuhan.TeamScheduleManagement.domain.TeamApply;

public interface TeamApplyRepository extends JpaRepository<TeamApply, Integer>{
	boolean existsByApplierAndProjectLeader(String applier, String projectLeader);
}
