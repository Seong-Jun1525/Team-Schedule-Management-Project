package com.yuhan.TeamScheduleManagement.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yuhan.TeamScheduleManagement.domain.TestBoard;

public interface TestBoardRepository extends JpaRepository<TestBoard, Integer> {

}
