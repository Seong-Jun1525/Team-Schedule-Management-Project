package com.yuhan.TeamScheduleManagement.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yuhan.TeamScheduleManagement.domain.Project;
import com.yuhan.TeamScheduleManagement.domain.Team;
import com.yuhan.TeamScheduleManagement.domain.User;
import com.yuhan.TeamScheduleManagement.service.ProjectService;
import com.yuhan.TeamScheduleManagement.service.TeamService;
import com.yuhan.TeamScheduleManagement.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class MainController {
	
	@Autowired
	private ProjectService projectService;

	@Autowired
	private TeamService teamService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String home(
	        @RequestParam(defaultValue = "0") int page, 
	        @RequestParam(defaultValue = "10") int size, 
	        HttpSession session,
	        Model model) {

		// 세션 값 가져오기
		String userId = (String) session.getAttribute("userId");
		String userName = (String) session.getAttribute("userName");
		Team teamInfo;
		if(userId != null && userName != null) {
			model.addAttribute("userName", userName);
			User user = new User();
			user.setUserId(userId);
			Optional<User> existingUser = userService.getUser(user);
			int teamNo = existingUser.get().getTeamId();
			
			teamInfo = teamService.getTeamByTeamNum(teamNo);
		}
		else {
			teamInfo = null;
		}
		
		Project projectInfo = projectService.getAviableProject(userId);
		

		System.out.println("avaiableProject : " + projectInfo);
		
	    // 특정 상태의 프로젝트 페이징 처리
	    Page<Project> projectPage = projectService.getProjectState(Project.ProjectState.BEFORE, page, size);

	    model.addAttribute("projectList", projectPage.getContent());
	    model.addAttribute("currentPage", projectPage.getNumber());
	    model.addAttribute("totalPages", projectPage.getTotalPages());
	    model.addAttribute("totalItems", projectPage.getTotalElements());
	    model.addAttribute("projectInfo", projectInfo);
	    model.addAttribute("teamInfo", teamInfo);
	    
	    return "index";
	}
	
	@GetMapping("/err")
	public String error(Model model) {
		if (!model.containsAttribute("code")) {
	        model.addAttribute("code", "Unknown Error");
	    }
	    if (!model.containsAttribute("message")) {
	        model.addAttribute("message", "An unknown error occurred.");
	    }
	    return "error/error";
	}
}