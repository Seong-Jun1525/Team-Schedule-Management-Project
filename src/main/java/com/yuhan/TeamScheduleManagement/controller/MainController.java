package com.yuhan.TeamScheduleManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yuhan.TeamScheduleManagement.domain.Project;
import com.yuhan.TeamScheduleManagement.service.ProjectService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class MainController {
	
	@Autowired
	private ProjectService projectService;
	
	@GetMapping("/")
	public String home(
	        @RequestParam(defaultValue = "0") int page, 
	        @RequestParam(defaultValue = "10") int size, 
	        HttpSession session,
	        Model model) {

		// 세션 값 가져오기
		String userName = (String) session.getAttribute("userName");
		if(userName != null) {
			model.addAttribute("userName", userName);
		}
		
	    // 특정 상태의 프로젝트 페이징 처리
	    Page<Project> projectPage = projectService.getProjectState(Project.ProjectState.BEFORE, page, size);

	    // 모델에 페이징된 프로젝트 데이터와 페이지 정보를 추가
	    model.addAttribute("projectList", projectPage.getContent());
	    model.addAttribute("currentPage", projectPage.getNumber());
	    model.addAttribute("totalPages", projectPage.getTotalPages());
	    model.addAttribute("totalItems", projectPage.getTotalElements());
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