package com.yuhan.TeamScheduleManagement.repository;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yuhan.TeamScheduleManagement.domain.TestBoard;
import com.yuhan.TeamScheduleManagement.persistance.TestBoardRepository;

@SpringBootTest
public class BoardRepositoryTest {
	@Autowired
	TestBoardRepository testBoardRepo;
	
	@Test
	public void testInsertBoard() {
		TestBoard testBoard = new TestBoard();
		testBoard.setMyName("임성준");
		testBoardRepo.save(testBoard);
	}
	
//	@Test
	public void testGetBoard() {
		Optional<TestBoard> find = testBoardRepo.findById(1);
		TestBoard testBoard = find.get();
		System.out.println("-------------> " + testBoard.toString());
	}
}
