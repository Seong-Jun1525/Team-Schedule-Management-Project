package com.yuhan.TeamScheduleManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yuhan.TeamScheduleManagement.domain.Team;
import com.yuhan.TeamScheduleManagement.service.TeamServiceImpl;

@Controller
@RequestMapping("/team")
public class TeamController {
	
	@Autowired
	private TeamServiceImpl teamService;
	
	@GetMapping("/register")
	public String teamRegisterPage() {
		return "team/register";
	}
	
	@PostMapping("/register")
	public String teamRegister(Team team) {
		try {
			System.out.println(team);
			teamService.insertTeam(team);
			return "redirect:/";
		} catch(Error error) {
			System.out.println("Error : " + error.getMessage());
			return "redirect:/error";
		}
	}
}
