package com.yuhan.TeamScheduleManagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/my-team")
public class MyTeamController {
	
	@GetMapping("/")
	public String myTeamMain() {
		return "my_team/myTeamMain";
	}

	@GetMapping("/home")
	public String myTeamHome() {
		return "my_team/myTeamHome";
	}
	
	@GetMapping("/member")
	public String myTeamMember() {
		return "my_team/myTeamMember";
	}
	
	@GetMapping("/board")
	public String myTeamBoard() {
		return "my_team/myTeamBoard";
	}
	
	@GetMapping("/schedule")
	public String myTeamSchedule() {
		return "my_team/myTeamSchedule";
	}
	
	@GetMapping("/chat")
	public String myTeamChat() {
		return "my_team/myTeamChat";
	}
}
