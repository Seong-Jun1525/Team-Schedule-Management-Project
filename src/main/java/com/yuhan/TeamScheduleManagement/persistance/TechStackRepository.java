package com.yuhan.TeamScheduleManagement.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yuhan.TeamScheduleManagement.domain.TechStack;

public interface TechStackRepository extends JpaRepository<TechStack, Integer> {

}
