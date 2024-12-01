package com.yuhan.TeamScheduleManagement.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuhan.TeamScheduleManagement.domain.Team;
import com.yuhan.TeamScheduleManagement.domain.TeamApply;
import com.yuhan.TeamScheduleManagement.domain.User;
import com.yuhan.TeamScheduleManagement.service.ProjectService;
import com.yuhan.TeamScheduleManagement.service.TeamApplyService;
import com.yuhan.TeamScheduleManagement.service.TeamService;
import com.yuhan.TeamScheduleManagement.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/team-apply")
public class TeamApplyController {

    @Autowired
    private TeamApplyService teamApplyService;

    @Autowired
    private UserService userService;
    
    @Autowired
    private TeamService teamService;
    
    @Autowired
    private ProjectService projectService;

    @PostMapping("/apply")
    @ResponseBody
    public Map<String, String> applyToTeam(@RequestBody Map<String, String> requestBody, HttpSession session) {
        Map<String, String> response = new HashMap<>();

        try {
            // 세션에서 userId 가져오기
            String userId = (String) session.getAttribute("userId");

            if (userId == null) {
                response.put("status", "error");
                response.put("message", "로그인이 필요합니다.");
                return response;
            }

            // 세션이 존재하는 경우 사용자 정보 가져오기
            User user = new User();
            user.setUserId(userId);
            Optional<User> existingUser = userService.getUser(user);

            if (existingUser.isEmpty()) {
                response.put("status", "error");
                response.put("message", "유효하지 않은 사용자입니다.");
                return response;
            }

            User foundUser = existingUser.get();

            // 이미 팀에 속해있는지 확인
            if (foundUser.getTeamId() != 0) {
                response.put("status", "error");
                response.put("message", "이미 팀에 소속되어 있습니다.");
                return response;
            }

            // 요청 본문에서 projectLeader 가져오기
            String projectLeader = requestBody.get("projectLeader");
            if (projectLeader == null || projectLeader.isEmpty()) {
                response.put("status", "error");
                response.put("message", "프로젝트 리더 정보가 누락되었습니다.");
                return response;
            }
            
            // 이미 신청 여부 확인
            boolean alreadyApplied = teamApplyService.isAlreadyApplied(foundUser.getUserId(), projectLeader);
            if (alreadyApplied) {
                response.put("status", "error");
                response.put("message", "이미 해당 프로젝트에 팀원 신청을 하셨습니다.");
                return response;
            }

            // 팀 신청 데이터 생성
            TeamApply teamApply = new TeamApply();
            teamApply.setApplier(foundUser.getUserId());
            teamApply.setProjectLeader(projectLeader);

            // 팀 신청 처리
            teamApplyService.applyForTeam(teamApply);

            response.put("status", "success");
            response.put("message", "팀원 신청이 완료되었습니다.");

        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "서버 오류가 발생했습니다. 다시 시도해주세요.");
            e.printStackTrace(); // 서버 로그
        }

        return response;
    }
    
    
    @PostMapping("/accept")
    @ResponseBody
    public Map<String, String> acceptTeamApplier(@RequestParam String applier, HttpSession session) {
        Map<String, String> response = new HashMap<>();
        System.out.println("Applier: " + applier);


        try {
            // 세션에서 로그인한 사용자 ID 확인
            String userId = (String) session.getAttribute("userId");

            if (userId == null) {
                response.put("status", "error");
                response.put("message", "로그인이 필요합니다.");
                return response;
            }

            // 로그인한 사용자 팀 정보 가져오기
            Team team = teamService.getTeam(userId);

            if (team == null) {
                response.put("status", "error");
                response.put("message", "팀 정보가 없습니다.");
                return response;
            }

            // 신청자의 팀 정보를 업데이트
            boolean updated = teamApplyService.acceptApplication(applier, team.getTeamNum());
            if (!updated) {
                response.put("status", "error");
                response.put("message", "팀원 신청 수락에 실패했습니다.");
                return response;
            }
            
            // 프로젝트 인원 증가
            projectService.incrementNumberOfPeople(userId);

            response.put("status", "success");
            response.put("message", "팀원 신청을 수락했습니다.");
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "서버 오류가 발생했습니다.");
            e.printStackTrace();
        }

        return response;
    }

    @PostMapping("/reject")
    @ResponseBody
    public Map<String, String> rejectTeamApplier(@RequestParam String applier) {
        Map<String, String> response = new HashMap<>();
        System.out.println("Applier: " + applier);


        try {
            // 신청 데이터를 삭제
            boolean deleted = teamApplyService.rejectApplication(applier);
            if (!deleted) {
                response.put("status", "error");
                response.put("message", "팀원 신청 거절에 실패했습니다.");
                return response;
            }

            response.put("status", "success");
            response.put("message", "팀원 신청을 거절했습니다.");
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "서버 오류가 발생했습니다.");
            e.printStackTrace();
        }

        return response;
    }

    
    
    
}

