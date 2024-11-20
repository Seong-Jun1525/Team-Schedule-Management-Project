package com.yuhan.TeamScheduleManagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/error")
	public String error() {
		return "error/error";
	}
}