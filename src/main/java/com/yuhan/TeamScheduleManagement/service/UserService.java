package com.yuhan.TeamScheduleManagement.service;

import java.util.ArrayList;
import java.util.Optional;

import com.yuhan.TeamScheduleManagement.domain.User;

public interface UserService {
	public void insertUser(User user);
	public void updateUser(User user);
	public void updateUserStateLogin(User user);
	public void updateUserStateLogout(User user);
	public Optional<User> getUser(User user);
}
