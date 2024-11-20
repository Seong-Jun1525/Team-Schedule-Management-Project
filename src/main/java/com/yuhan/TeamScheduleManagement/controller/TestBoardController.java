package com.yuhan.TeamScheduleManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yuhan.TeamScheduleManagement.domain.TestBoard;
import com.yuhan.TeamScheduleManagement.persistance.TestBoardRepository;

@Controller
public class TestBoardController {
	@Autowired
	TestBoardRepository testBoardRepo;
	
	@GetMapping("/testBoard/testBoardList")
	public String testBoardList(Model model) {
		List<TestBoard> testBoardList = testBoardRepo.findAll();
		
		model.addAttribute("testBoardList", testBoardList);
		return "testBoard/testBoardList";
	}
	
	@GetMapping("/testBoard/insertData")
	public String insertDataPage() {
		return "testBoard/insertData";
	}
	@PostMapping("/testBoard/insertData")
	public String insertData(@RequestParam("myName") String name, Model model) {
		TestBoard testBoard = new TestBoard();
		testBoard.setMyName(name);
		testBoardRepo.save(testBoard);
		System.out.println("---------------데이터 추가---------------");

		return "index";
	}
}
