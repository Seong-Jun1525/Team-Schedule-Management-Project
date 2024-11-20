package com.yuhan.TeamScheduleManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String teamRegister(Team team, RedirectAttributes redirectAttributes) {
		try {
//			System.out.println(team);
			teamService.insertTeam(team);
			return "redirect:/";
		} catch (Exception ex) {
			String errorMessage = ex.getMessage();
	        System.out.println("Error : " + HttpStatus.NOT_FOUND.toString());
	        System.out.println("Error : " + errorMessage);
	        redirectAttributes.addFlashAttribute("code", HttpStatus.NOT_FOUND.toString());
	        redirectAttributes.addFlashAttribute("message", errorMessage);
	        return "redirect:/err";
		}
	}
}
