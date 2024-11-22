package com.yuhan.TeamScheduleManagement.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yuhan.TeamScheduleManagement.domain.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

}