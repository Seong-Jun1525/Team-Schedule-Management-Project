package com.yuhan.TeamScheduleManagement.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yuhan.TeamScheduleManagement.domain.User;

public interface UserRepository extends JpaRepository<User, String>{
	
}