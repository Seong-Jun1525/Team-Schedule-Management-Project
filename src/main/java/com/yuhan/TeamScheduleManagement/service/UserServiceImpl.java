package com.yuhan.TeamScheduleManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuhan.TeamScheduleManagement.domain.User;
import com.yuhan.TeamScheduleManagement.domain.User.UserState;
import com.yuhan.TeamScheduleManagement.persistance.UserRepository;
import com.yuhan.TeamScheduleManagement.util.HashUtil;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public void insertUser(User user) {
		// TODO 회원가입 기능
		String hashedPw = HashUtil.hashPassword(user.getUserPw());
		user.setUserPw(hashedPw);
		userRepo.save(user);
	}

	@Override
	public void updateUser(User user) {
		// TODO 회원정보 수정 기능
	    // 회원 정보 수정 기능
	    if (user == null || user.getUserId() == null) {
	        throw new IllegalArgumentException("Invalid user data.");
	    }

	    // 기존 사용자 정보 가져오기
	    Optional<User> existingUser = userRepo.findById(user.getUserId());
	    if (existingUser.isEmpty()) {
	        throw new IllegalArgumentException("User not found.");
	    }

	    User updateUser = existingUser.get();

	    // 수정 가능한 필드 업데이트 (입력값 존재 시 업데이트)
	    if (user.getUserPw() != null && !user.getUserPw().isEmpty()) {
	        updateUser.setUserPw(HashUtil.hashPassword(user.getUserPw())); // 비밀번호 암호화
	    }
	    if (user.getUserName() != null) {
	        updateUser.setUserName(user.getUserName());
	    }
	    if (user.getUserEmail() != null) {
	        updateUser.setUserEmail(user.getUserEmail());
	    }
	    if (user.getUserGender() != null) {
	        updateUser.setUserGender(user.getUserGender());
	    }
	    if (user.getUserJob() != null) {
	        updateUser.setUserJob(user.getUserJob());
	    }

	    // 변경사항 저장
	    userRepo.save(updateUser);
		
	}
	
	@Override
	public void updateUserStateLogin(User user) {
		// TODO 로그인 시 회원 상태 변경
        user.setUserState(UserState.ONLINE);
        userRepo.save(user);
	}

	@Override
	public void updateUserStateLogout(User user) {
		// TODO 로그아웃 시 회원 상태 변경
        user.setUserState(UserState.OFFLINE);
        userRepo.save(user);
	}
	
	@Override
	public void deleteUser(String userId) {
		// TODO 회원탈퇴 기능
	    try {
	        userRepo.deleteById(userId);
	    } catch (Exception e) {
	        throw new RuntimeException("회원 탈퇴 중 오류 발생: " + e.getMessage());
	    }
	}

	
	@Override
	public Optional<User> getUser(User user) {
		// TODO 회원정보 가져오기 기능
		Optional<User> userInfo = userRepo.findById(user.getUserId());
		return userInfo;
	}

	@Override
	@Transactional
	public void updateTeamId(String userId, int teamId) {
		// TODO 사용자의 teamId 값 업데이트
	    User user = userRepo.findById(userId)
	        .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

	    user.setTeamId(teamId);

	    userRepo.save(user);
	}
	
    @Override
    public List<User> getTeamMembers(int teamId) {
        return userRepo.findByTeamId(teamId);
    }
}
