package com.yuhan.TeamScheduleManagement.persistance;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yuhan.TeamScheduleManagement.domain.TeamBoard;

public interface TeamBoardRepository extends JpaRepository<TeamBoard, Integer> {

	void save(Optional<TeamBoard> existingTeamBoard);

}
