package com.yuhan.TeamScheduleManagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/sign-up")
    public String signUpPage() {
    	// 회원가입 구현 로직
        return "member/signUp";
    }

    @GetMapping("/sign-in")
    public String signInPage() {
    	// 로그인 구현 로직
        return "member/signIn";
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
