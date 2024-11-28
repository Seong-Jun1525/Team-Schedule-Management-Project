package com.yuhan.TeamScheduleManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yuhan.TeamScheduleManagement.domain.Team;
import com.yuhan.TeamScheduleManagement.domain.TeamBoard;
import com.yuhan.TeamScheduleManagement.service.TeamBoardService;
import com.yuhan.TeamScheduleManagement.service.TeamService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/my-team")
public class MyTeamController {
	
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private TeamBoardService teamBoardService;
	
	@GetMapping("/")
	public String myTeamMain(HttpSession session, Model model) {
		String userId = (String) session.getAttribute("userId");
		String userName = (String) session.getAttribute("userName");
		
		Team myTeamInfo = teamService.getTeam(userId);
		
		model.addAttribute("userName", userName);
		model.addAttribute("myTeamInfo", myTeamInfo);
		
		return "my_team/myTeamMain";
	}

	@GetMapping("/home")
	public String myTeamHome() {
		return "my_team/myTeamHome";
	}
	
	@GetMapping("/member")
	public String myTeamMember() {
		return "my_team/myTeamMember";
	}
	
	@GetMapping("/board")
	public String myTeamBoard(
		@RequestParam(defaultValue = "0") int page, 
        @RequestParam(defaultValue = "10") int size, 
        HttpSession session,
        Model model
    ) {
		String userId = (String) session.getAttribute("userId");
		String userName = (String) session.getAttribute("userName");

		Team myTeamInfo = teamService.getTeam(userId);
		
		Page<TeamBoard> teamBoardList = teamBoardService.getAllTeamBoards(page, size);
		
		model.addAttribute("myTeamInfo", myTeamInfo);
		model.addAttribute("teamBoardList", teamBoardList);
		model.addAttribute("currentPage", teamBoardList.getNumber());
	    model.addAttribute("totalPages", teamBoardList.getTotalPages());
	    model.addAttribute("totalItems", teamBoardList.getTotalElements());
		
		return "my_team/myTeamBoard";
	}
	
	// 글 등록 페이지
	@GetMapping("/board/insert")
	public String insertTeamBoardPage(HttpSession session, Model model) {
		String userName = (String) session.getAttribute("userName");
		
		model.addAttribute("userName", userName);
		
		return "my_team/board/insertBoard";
	}
	
	// 글 등록 기능
	@PostMapping("/board/insert")
	public String insertTeamBoard(HttpSession session, TeamBoard teamBoard) {
		System.out.println(teamBoard);
		if(teamBoard != null) {
			String memberId = (String) session.getAttribute("userId");
			teamBoardService.teamBoardInsert(teamBoard, memberId);
			return "redirect:/";
		}
		else {
			return "error/error";
		}
	}
	
	// 글 상세 페이지
	@GetMapping("/board/detail")
	public String teamBoardDetail(@RequestParam int teamBoardId, Model model) {
		TeamBoard teamBoardInfo = teamBoardService.getTeamBoard(teamBoardId);
		System.out.println(teamBoardInfo);
		
		model.addAttribute("teamBoardInfo", teamBoardInfo);
		
		return "my_team/board/teamBoardDetail";
	}
	
	// 글 수정 페이지
	@GetMapping("/board/update")
	public String teamBoardUpdatePage(@RequestParam int teamBoardId, HttpSession session, Model model) {
		TeamBoard teamBoardInfo = teamBoardService.getTeamBoard(teamBoardId);
		System.out.println(teamBoardInfo);
		String userName = (String) session.getAttribute("userName");
		
		model.addAttribute("userName", userName);
		model.addAttribute("teamBoardInfo", teamBoardInfo);
		
		return "my_team/board/updateBoard";
	}
	
	// 글 수정 기능
	@PostMapping("/board/update")
	public String updateTeamBoard(HttpSession session, TeamBoard teamBoard) {
		System.out.println("teamBoard : " + teamBoard);
		if(teamBoard != null) {
			String memberId = (String) session.getAttribute("userId");
			teamBoardService.teamBoardUpdate(teamBoard);
			return "redirect:/";
		}
		else {
			return "error/error";
		}
	}
	
	// 글 삭제 기능
	@PostMapping("/board/delete")
	public String deleteTeamBoard(int teamBoardId, HttpSession session) {
		if(teamBoardId != 0) {
			String memberId = (String) session.getAttribute("userId");
			teamBoardService.teamBoardDelete(teamBoardId);
			return "redirect:/";
		}
		else {
			return "error/error";
		}
	}
	
	@GetMapping("/schedule")
	public String myTeamSchedule(@RequestParam int teamNum, Model model) {
		// System.out.println(teamNum);
		Team myTeamInfo = teamService.getTeamByTeamNum(teamNum);
		model.addAttribute("myTeamInfo", myTeamInfo);
		return "my_team/myTeamSchedule";
	}
	
	@GetMapping("/chat")
	public String myTeamChat() {
		return "my_team/myTeamChat";
	}
}
