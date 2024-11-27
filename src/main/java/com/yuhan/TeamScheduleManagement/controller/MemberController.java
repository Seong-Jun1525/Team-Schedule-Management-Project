package com.yuhan.TeamScheduleManagement.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuhan.TeamScheduleManagement.domain.User;
import com.yuhan.TeamScheduleManagement.domain.User.UserState;
import com.yuhan.TeamScheduleManagement.service.UserService;
import com.yuhan.TeamScheduleManagement.util.HashUtil;

import jakarta.servlet.http.HttpSession;


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
    public Map<String, String> signIn(@RequestBody User user, HttpSession session) {
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
            userService.updateUserStateLogin(foundUser);
            
            session.setAttribute("userId", foundUser.getUserId()); // 세션에 사용자 ID 저장
            session.setAttribute("userName", foundUser.getUserName()); // 세션에 사용자 이름 저장
            
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
    public String myInfoPage(HttpSession session, Model model) {
    	// 마이페이지 로직
        // 세션에서 userId 확인
        String userId = (String) session.getAttribute("userId");

        if (userId == null) {
            // 세션이 없는 경우 로그인 페이지로 리다이렉트
            return "redirect:/member/sign-in";
        }

        // 세션이 존재하는 경우 사용자 정보 가져오기
        User user = new User();
        user.setUserId(userId);
        Optional<User> existingUser = userService.getUser(user);

        if (existingUser.isPresent()) {
            // 사용자 정보를 모델에 추가
            model.addAttribute("userInfo", existingUser.get());
            return "member/myInfo";
        } else {
            // 사용자가 없는 경우 에러 페이지나 기본 페이지로 이동
            return "redirect:/";
        }
    }

    
    @PostMapping("/update-user-info")
    @ResponseBody
    public Map<String, String> updateUserInfo(@RequestBody User user, HttpSession session) {
        Map<String, String> response = new HashMap<>();

        try {
            // 세션에서 userId 확인
            String userId = (String) session.getAttribute("userId");

            if (userId == null) {
                response.put("status", "error");
                response.put("message", "로그인이 필요합니다.");
                return response;
            }

            // 업데이트 요청된 userId가 세션과 일치하는지 확인
            if (!userId.equals(user.getUserId())) {
                response.put("status", "error");
                response.put("message", "잘못된 요청입니다.");
                return response;
            }

            // 사용자 정보 업데이트
            userService.updateUser(user);

            // 수정된 정보를 다시 가져와 세션 값 업데이트
            Optional<User> updatedUserOpt = userService.getUser(user);
            if (updatedUserOpt.isPresent()) {
                User updatedUser = updatedUserOpt.get();

                // 세션 값 갱신
                session.setAttribute("userId", updatedUser.getUserId());
                session.setAttribute("userName", updatedUser.getUserName());

                response.put("status", "success");
                response.put("message", "회원정보가 성공적으로 업데이트되었습니다.");
            } else {
                response.put("status", "error");
                response.put("message", "업데이트된 사용자 정보를 가져올 수 없습니다.");
            }
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "서버 오류가 발생했습니다. 다시 시도해주세요.");
            e.printStackTrace();
        }

        return response;
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 1. 세션에서 사용자 ID 가져오기
        String userId = (String) session.getAttribute("userId");

        if (userId != null) {
            // 2. 데이터베이스에서 사용자 정보 가져오기
            User user = new User();
            user.setUserId(userId);
            Optional<User> existingUser = userService.getUser(user);

            if (existingUser.isPresent()) {
                User foundUser = existingUser.get();
                foundUser.setUserState(UserState.OFFLINE);
                userService.updateUserStateLogout(foundUser);
            }
        }

        // 4. 세션 무효화
        session.invalidate();

        // 5. 메인 페이지로 리다이렉트
        return "redirect:/";
    }

}
