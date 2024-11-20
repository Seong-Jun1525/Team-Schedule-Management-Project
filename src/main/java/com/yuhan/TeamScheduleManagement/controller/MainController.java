package com.yuhan.TeamScheduleManagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class MainController {
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/err")
	public String error(
			@RequestParam(required = false) String message, 
			@RequestParam(required = false) String code,
			Model model
		) {
		model.addAttribute("code", code != null ? code : "No Code");
		model.addAttribute("message", message != null ? message : "An unknown error occurred.");
	    return "error/error";
	}
}