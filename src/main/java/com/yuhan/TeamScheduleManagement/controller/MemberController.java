package com.yuhan.TeamScheduleManagement.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yuhan.TeamScheduleManagement.domain.User;
import com.yuhan.TeamScheduleManagement.service.UserService;
import com.yuhan.TeamScheduleManagement.util.HashUtil;


@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private UserService userService;

    @GetMapping("/sign-up")
    public String signUpPage() {
    	// 회원가입 구현 로직
        return "member/signUp";
    }
    
    @PostMapping("/sign-up")
    @ResponseBody
    public Map<String, String> signUp(@RequestBody User user) {
        Map<String, String> response = new HashMap<>();
        try {
            Optional<User> existingUser = userService.getUser(user);

            if (existingUser.isPresent()) {
                response.put("status", "error");
                response.put("message", "이미 사용 중인 아이디입니다.");
                return response;
            }

            // 회원가입 성공
            userService.insertUser(user);
            response.put("status", "success");
            response.put("message", "회원가입이 완료되었습니다.");
        } catch (Exception e) {
            // 서버 오류 처리
            response.put("status", "error");
            response.put("message", "서버에 문제가 발생했습니다. 다시 시도해주세요.");
            // 로그 출력
            e.printStackTrace();
        }
        return response;
    }

    @GetMapping("/sign-in")
    public String signInPage() {
    	// 로그인 구현 로직
        return "member/signIn";
    }
    
    @PostMapping("/sign-in")
    @ResponseBody
    public Map<String, String> signIn(@RequestBody User user) {
    	Map<String, String> response = new HashMap<>();
        try {
            // 아이디로 사용자 검색
            Optional<User> existingUser = userService.getUser(user);

            if (existingUser.isEmpty()) {
                // 아이디가 존재하지 않을 경우
                response.put("status", "error");
                response.put("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
                return response;
            }

            // 비밀번호 확인
            User foundUser = existingUser.get();
            String hashedInputPw = HashUtil.hashPassword(user.getUserPw()); // 입력된 비밀번호 암호화
            if (!foundUser.getUserPw().equals(hashedInputPw)) {
                // 비밀번호가 일치하지 않을 경우
                response.put("status", "error");
                response.put("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
                return response;
            }

            // 로그인 성공
            response.put("status", "success");
            response.put("message", "로그인에 성공했습니다.");
            // 필요시 세션이나 토큰 생성 로직 추가 가능
        } catch (Exception e) {
            // 서버 오류 처리
            response.put("status", "error");
            response.put("message", "서버에 문제가 발생했습니다. 다시 시도해주세요.");
            e.printStackTrace(); // 로그 출력
        }
        return response;   	
    }
    
    @GetMapping("/my-info")
    public String myInfoPage() {
    	// 로그인 구현 로직
        return "member/myInfo";
    }

    @GetMapping("/logout")
    public String logout() {
    	// 로그아웃 구현 로직
        return "redirect:/";
    }
}
