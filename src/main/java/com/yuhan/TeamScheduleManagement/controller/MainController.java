package com.yuhan.TeamScheduleManagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class MainController {
	@GetMapping("/")
	public String home(HttpSession session, Model model) {
		// 세션 값 가져오기
		String userName = (String) session.getAttribute("userName");
		if(userName != null) {
			model.addAttribute("userName", userName);
		}
		return "index";
	}
}