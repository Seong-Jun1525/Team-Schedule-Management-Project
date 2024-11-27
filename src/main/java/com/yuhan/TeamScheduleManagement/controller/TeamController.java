package com.yuhan.TeamScheduleManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yuhan.TeamScheduleManagement.domain.Team;
import com.yuhan.TeamScheduleManagement.service.TeamServiceImpl;
import com.yuhan.TeamScheduleManagement.service.UserServiceImpl;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/team")
public class TeamController {
	
	@Autowired
	private TeamServiceImpl teamService;
	
	@Autowired
	private UserServiceImpl userService;
	
	@GetMapping("/register")
	public String teamRegisterPage(HttpSession session, Model model) {
		System.out.println("session : " + (String) session.getAttribute("userId"));
		String userId = (String) session.getAttribute("userId");
		System.out.println("session userId : " + userId);
		model.addAttribute("userId", userId);
		return "team/register";
	}
	
	@PostMapping("/register")
	public String teamRegister(Team team, RedirectAttributes redirectAttributes) {
		try {
			System.out.println(team);
			teamService.insertTeam(team);
			userService.updateTeamId(team.getMemberId(), team.getTeamNum());
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
