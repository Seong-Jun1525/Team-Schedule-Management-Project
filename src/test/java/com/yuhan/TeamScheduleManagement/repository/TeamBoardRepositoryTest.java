package com.yuhan.TeamScheduleManagement.repository;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yuhan.TeamScheduleManagement.domain.TeamBoard;
import com.yuhan.TeamScheduleManagement.persistance.TeamBoardRepository;

@SpringBootTest
public class TeamBoardRepositoryTest {

	@Autowired
	TeamBoardRepository teamBoardRepo;
	
//	@Test
	public void insertTeamBoardTest() {
		TeamBoard teamBoard = new TeamBoard();
		teamBoard.setTeamBoardTitle("testTitleJun");
		teamBoard.setTeamBoardMemberId("testMemberJun");
		teamBoard.setTeamBoardWriter("testJun");
		teamBoard.setTeamBoardContent("Hello World Test");
		
		teamBoardRepo.save(teamBoard);
	}
	
//	@Test
	public void deleteTeamBoardTest() {
		// Optional<TeamBoard> teamBoard = teamBoardRepo.findById(1);
		// System.out.println(teamBoard);
		teamBoardRepo.deleteById(1);
	}
}
