package com.yuhan.TeamScheduleManagement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuhan.TeamScheduleManagement.domain.User;
import com.yuhan.TeamScheduleManagement.domain.User.UserState;
import com.yuhan.TeamScheduleManagement.persistance.UserRepository;
import com.yuhan.TeamScheduleManagement.util.HashUtil;

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
		
	}
	


	@Override
	public Optional<User> getUser(User user) {
		// 회원정보 가져오기 기능
		Optional<User> userInfo = userRepo.findById(user.getUserId());
		return userInfo;
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

}
