package com.yuhan.TeamScheduleManagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
	@GetMapping("/")
	public String home() {
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