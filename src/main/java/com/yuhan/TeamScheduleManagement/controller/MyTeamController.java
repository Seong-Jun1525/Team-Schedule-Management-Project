package com.yuhan.TeamScheduleManagement.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuhan.TeamScheduleManagement.domain.Team;
import com.yuhan.TeamScheduleManagement.domain.TeamApply;
import com.yuhan.TeamScheduleManagement.domain.TeamBoard;
import com.yuhan.TeamScheduleManagement.domain.TeamSchedule;

import com.yuhan.TeamScheduleManagement.domain.User;
import com.yuhan.TeamScheduleManagement.service.TeamApplyService;
import com.yuhan.TeamScheduleManagement.service.TeamBoardService;
import com.yuhan.TeamScheduleManagement.service.TeamScheduleService;
import com.yuhan.TeamScheduleManagement.service.TeamService;
import com.yuhan.TeamScheduleManagement.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/my-team")
public class MyTeamController {
	
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private TeamBoardService teamBoardService;
	
	@Autowired
	private TeamScheduleService teamScheduleService;
	
	@Autowired
	private TeamApplyService teamApplyService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String myTeamMain(HttpSession session, Model model) {
		String userId = (String) session.getAttribute("userId");
		String userName = (String) session.getAttribute("userName");
		
		Team myTeamInfo;
		User user = new User();
		user.setUserId(userId);
		Optional<User> existingUser = userService.getUser(user);
		int teamNo = existingUser.get().getTeamId();
		
		myTeamInfo = teamService.getTeamByTeamNum(teamNo);
		
		model.addAttribute("userName", userName);
		model.addAttribute("myTeamInfo", myTeamInfo);
		
		return "my_team/myTeamMain";
	}

	@GetMapping("/home")
	public String myTeamHome() {
		return "my_team/myTeamHome";
	}
	
	@GetMapping("/member")
	public String myTeamMember(HttpSession session, Model model) {
	    // 세션에서 userId 가져오기
	    String userId = (String) session.getAttribute("userId");

	    if (userId == null) {
	        return "redirect:/member/sign-in"; // 로그인 필요
	    }

	    // 팀 리더 여부 확인
		Team myTeamInfo;
		User user = new User();
		user.setUserId(userId);
		Optional<User> existingUser = userService.getUser(user);
		int teamNo = existingUser.get().getTeamId();
		myTeamInfo = teamService.getTeamByTeamNum(teamNo);

	    if (!myTeamInfo.getMemberId().equals(userId)) {
	        model.addAttribute("error", "팀 리더만 신청 현황을 볼 수 있습니다.");
	        return "my_team/myTeamMain"; // 메인 페이지로 이동
	    }
	    
	    // 팀 멤버 가져오기
	    List<User> teamMembers = userService.getTeamMembers(teamNo);

	    // 신청 현황 가져오기
	    List<TeamApply> teamAppliers = teamApplyService.getApplicationsForLeader(userId);

	    // 모델에 데이터 추가
	    model.addAttribute("teamMembers", teamMembers); // 팀 멤버
	    model.addAttribute("teamAppliers", teamAppliers);

	    return "my_team/myTeamMember"; // 뷰 이름
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

		Team myTeamInfo;
		User user = new User();
		user.setUserId(userId);
		Optional<User> existingUser = userService.getUser(user);
		int teamNo = existingUser.get().getTeamId();
		
		myTeamInfo = teamService.getTeamByTeamNum(teamNo);
		
		myTeamInfo.getMemberId();
		
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
	public String getSchedules(@RequestParam int teamNum, Model model) {
	    System.out.println("Received teamNum: " + teamNum); // 요청 로그 추가

	    // 일정을 가져옵니다
	    List<TeamSchedule> schedules = teamScheduleService.getSchedulesByTeamNum(teamNum);
	    
	    // 팀 정보를 가져옵니다
	    Team teamInfo = teamService.getTeamByTeamNum(teamNum);
	    
	    System.out.println(schedules);
	    System.out.println(teamInfo);
	    
	    // 모델에 데이터를 추가합니다
	    model.addAttribute("schedules", schedules);
	    model.addAttribute("teamInfo", teamInfo);
	    model.addAttribute("teamNum", teamNum);
	    
	    // "myTeamSchedule" 페이지를 렌더링합니다
	    return "my_team/myTeamSchedule";
	}
	
	@GetMapping("/schedule/events")
	@ResponseBody
	public List<Map<String, Object>> getScheduleEvents(@RequestParam int teamNum) {
	    System.out.println("Received teamNum: " + teamNum);

	    // 일정을 가져옵니다
	    List<TeamSchedule> schedules = teamScheduleService.getSchedulesByTeamNum(teamNum);

	    // 데이터를 FullCalendar 이벤트 형식으로 변환
	    List<Map<String, Object>> events = new ArrayList<>();
	    for (TeamSchedule schedule : schedules) {
	        Map<String, Object> event = new HashMap<>();
	        event.put("id", schedule.getTeamScheduleId());
//	        event.put("title", schedule.getTeamScheduleContent());
	        event.put("title", schedule.getTeamScheduleState());
	        event.put("start", schedule.getTeamScheduleStartDate());
	        event.put("end", schedule.getTeamScheduleEndDate());
	        event.put("allDay", schedule.getAllDay()); // isAllDay() 메서드 확인
	        events.add(event);
	    }

	    System.out.println("Events for calendar: " + events);
	    return events;
	}
	
	@GetMapping("/schedule/event")
	public List<TeamSchedule> getScheduleEvent(@RequestParam int teamNum, @RequestParam int teamScheduleId) {
		List<TeamSchedule> schedule = teamScheduleService.getScheduleByTeamNumAndTeamScheduleId(teamNum, teamScheduleId);
		
		return schedule;
	}
	
	// 일정 추가
	@PostMapping("/schedule/insertSchedule")
	@ResponseBody
	public ResponseEntity<Map<String, String>> insertTeamSchedule(TeamSchedule teamSchedule, HttpSession session) {
	    try {
	    	System.out.println("teamSchedule insert : " + teamSchedule);
	        String memberId = (String) session.getAttribute("userId");
	        teamScheduleService.insertTeamSchedule(teamSchedule, memberId);
	        
	        Map<String, String> response = new HashMap<>();
	        response.put("message", "Schedule inserted successfully!");
	        return ResponseEntity.ok(response);
	    } catch (Exception e) {
	        e.printStackTrace();
	        Map<String, String> response = new HashMap<>();
	        response.put("message", "Error inserting schedule.");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}

	// 일정 삭제
	@PostMapping("/schedule/deleteSchedule")
    @ResponseBody
    public ResponseEntity<String> deleteSchedule(@RequestParam int scheduleId) {
        try {
            teamScheduleService.deleteSchedule(scheduleId);
            return ResponseEntity.ok("삭제 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제 실패");
        }
    }

	// 일정 수정
	@PutMapping("/schedule/update")
	public ResponseEntity<?> updateSchedule(@RequestBody TeamSchedule teamSchedule) {
	    try {
	        if (teamSchedule == null || teamSchedule.getTeamScheduleId() == 0) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid schedule data");
	        }
	        boolean exists = teamScheduleService.getSchedulesByTeamNum(teamSchedule.getTeamScheduleId()).size() > 0;
	        if (!exists) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Schedule not found");
	        }
	        teamScheduleService.updateSchedule(teamSchedule);
	        return ResponseEntity.ok("Schedule updated successfully");
	    } catch (Exception e) {
	        e.printStackTrace(); // 로그 추가 필요
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating schedule");
	    }
	}
	
	@GetMapping("/chat")
	public String myTeamChat() {
		return "my_team/myTeamChat";
	}
}
