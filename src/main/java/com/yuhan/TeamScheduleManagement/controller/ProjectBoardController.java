package com.yuhan.TeamScheduleManagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project-board")
public class ProjectBoardController {
	@GetMapping("/")
	public String projectBoardDetailMain() {
		return "project_board/projectBoardDetail";
	}
}
