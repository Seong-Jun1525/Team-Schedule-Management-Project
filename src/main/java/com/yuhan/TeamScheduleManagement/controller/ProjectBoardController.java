package com.yuhan.TeamScheduleManagement.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yuhan.TeamScheduleManagement.domain.Project;
import com.yuhan.TeamScheduleManagement.domain.Team;
import com.yuhan.TeamScheduleManagement.domain.TechStack;
import com.yuhan.TeamScheduleManagement.persistance.ProjectRepository;
import com.yuhan.TeamScheduleManagement.service.ProjectService;
import com.yuhan.TeamScheduleManagement.service.TeamService;
import com.yuhan.TeamScheduleManagement.service.TechStackService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/project-board")
public class ProjectBoardController {
	
	@Autowired
	ProjectRepository projectRepo;
	
	@Autowired
    private TechStackService techStackService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private TeamService teamService;
	
	@GetMapping("/register")
	public String projectBoardRegisterPage(HttpSession session, Model model) {
		String userId = (String) session.getAttribute("userId");
		System.out.println("session userId : " + userId);
		
		// TODO team 테이블의 해당 user의 team 정보를 가져오기 (team_state가 1인 경우만)
		Team teamInfo = teamService.getTeam(userId);
		List<TechStack> techStacks = techStackService.getAllTechStacks();
        
		model.addAttribute("userId", userId);
        model.addAttribute("techStacks", techStacks);
        model.addAttribute("teamInfo", teamInfo);
		return "project_board/projectBoardRegister";
	}

	@PostMapping("/register")
	public String projectBoardRegister(
	        Project project,
	        @RequestParam(value = "techStacks", required = false) String[] techStacks, 
	        RedirectAttributes redirectAttributes
    ) {

	    // 기술 스택을 List<String>으로 변환
	    List<String> techStackList = new ArrayList<>();
	    if (techStacks != null) {
	        for (String techStackName : techStacks) {
	            techStackList.add(techStackName); // 기술 스택 추가
	        }
	    }
	    
	    projectService.projectRegister(techStackList, project);

	    // 리다이렉트
	    redirectAttributes.addFlashAttribute("message", "Project successfully registered!");
	    return "redirect:/";
	}

	@GetMapping("/search")
	public String searchProjects(
	        @RequestParam("projectName") String projectName,
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "10") int size,
	        Model model) {
	    Page<Project> searchResults = projectService.searchProjects(projectName, page, size);
	    
	    System.out.println("searchResults : " + searchResults.getContent());

	    model.addAttribute("projectList", searchResults.getContent());
	    model.addAttribute("currentPage", searchResults.getNumber());
	    model.addAttribute("totalPages", searchResults.getTotalPages());
	    return "index";
	}

	@GetMapping("/detail")
	public String projectBoardDetail(@RequestParam int projectId, Model model) {
		System.out.println("projectId : " + projectId);
		Optional<Project> project = projectService.getProject(projectId);
	    if (project.isPresent()) {
	        model.addAttribute("project", project.get());
	    }
	    else {
	        return "error/error";
	    }
	    return "project_board/projectBoardDetail";
	}

}