package com.yuhan.TeamScheduleManagement.persistance;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yuhan.TeamScheduleManagement.domain.Project;
import com.yuhan.TeamScheduleManagement.domain.Project.ProjectState;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
	Page<Project> findByProjectState(ProjectState state, Pageable paging);

	Page<Project> findByProjectNameContainingIgnoreCase(String projectName, Pageable pageable);

	Project findByProjectLeaderAndProjectState(String userId, ProjectState checkStateValue);
	
	@Modifying
	@Query("UPDATE Project p SET p.numberOfPeople = p.numberOfPeople + 1 WHERE p.projectLeader = :projectLeader")
	void incrementNumberOfPeople(@Param("projectLeader") String projectLeader);

}