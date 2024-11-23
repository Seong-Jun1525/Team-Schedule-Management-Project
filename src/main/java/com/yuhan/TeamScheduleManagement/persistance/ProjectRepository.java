package com.yuhan.TeamScheduleManagement.persistance;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.yuhan.TeamScheduleManagement.domain.Project;
import com.yuhan.TeamScheduleManagement.domain.Project.ProjectState;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
	Page<Project> findByProjectState(ProjectState state, Pageable paging);
	
}