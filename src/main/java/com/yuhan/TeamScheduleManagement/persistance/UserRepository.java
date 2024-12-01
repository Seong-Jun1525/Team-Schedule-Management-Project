package com.yuhan.TeamScheduleManagement.persistance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yuhan.TeamScheduleManagement.domain.User;

public interface UserRepository extends JpaRepository<User, String>{
	List<User> findByTeamId(int teamId);
}