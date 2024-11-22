package com.yuhan.TeamScheduleManagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yuhan.TeamScheduleManagement.domain.Project;
import com.yuhan.TeamScheduleManagement.domain.TechStack;
import com.yuhan.TeamScheduleManagement.persistance.ProjectRepository;
import com.yuhan.TeamScheduleManagement.service.TechStackService;

@Controller
@RequestMapping("/project-board")
public class ProjectBoardController {
	
	@Autowired
	ProjectRepository projectRepo;
	
	@Autowired
    private TechStackService techStackService;
	
	@GetMapping("/register")
	public String projectBoardRegisterPage(Model model) {
		List<TechStack> techStacks = techStackService.getAllTechStacks();
        model.addAttribute("techStacks", techStacks);
		return "project_board/projectBoardRegister";
	}
	
//	@PostMapping("/register")
//	public String projectBoardRegister(
//	        Project project,
//	        @RequestParam("techStacks") String[] techStacks, 
//	        RedirectAttributes redirectAttributes) {
//
//	    // 기술 스택을 List<String>으로 변환
//	    List<String> techStackList = new ArrayList<>();
//	    for (String techStackName : techStacks) {
//	        techStackList.add(techStackName); // 기술 스택 추가
//	    }
//	    
//	    // 프로젝트에 기술 스택 설정
//	    project.setTechStacks(techStackList);
//
//	    // 프로젝트 저장
//	    projectRepo.save(project);
//
//	    // 저장된 기술 스택 출력
//	    String techStacksString = String.join(", ", techStacks);
//	    System.out.println("Selected techStacks: " + techStacksString);
//
//	    // 리다이렉트
//	    return "redirect:/";
//	}

	@PostMapping("/register")
	public String projectBoardRegister(
	        Project project,
	        @RequestParam(value = "techStacks", required = false) String[] techStacks, 
	        RedirectAttributes redirectAttributes) {

	    // 기술 스택을 List<String>으로 변환
	    List<String> techStackList = new ArrayList<>();
	    if (techStacks != null) {
	        for (String techStackName : techStacks) {
	            techStackList.add(techStackName); // 기술 스택 추가
	        }
	    }

	    // 프로젝트에 기술 스택 설정
	    project.setTechStacks(techStackList);

	    // 프로젝트 저장
	    projectRepo.save(project);

	    // 저장된 기술 스택 출력
	    if (!techStackList.isEmpty()) {
	        String techStacksString = String.join(", ", techStackList);
	        System.out.println("Selected techStacks: " + techStacksString);
	    } else {
	        System.out.println("No techStacks selected.");
	    }

	    // 리다이렉트
	    redirectAttributes.addFlashAttribute("message", "Project successfully registered!");
	    return "redirect:/";
	}


	
	@GetMapping("/detail")
	public String projectBoardDetail() {
		return "project_board/projectBoardDetail";
	}
}