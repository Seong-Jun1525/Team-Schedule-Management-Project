package com.yuhan.TeamScheduleManagement.persistance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yuhan.TeamScheduleManagement.domain.TeamApply;

import jakarta.transaction.Transactional;

public interface TeamApplyRepository extends JpaRepository<TeamApply, Integer>{
	boolean existsByApplierAndProjectLeader(String applier, String projectLeader);
	List<TeamApply> findAllByProjectLeader(String projectLeader);
	@Transactional
	void deleteByApplier(String applier); // 신청자 ID로 삭제
}
