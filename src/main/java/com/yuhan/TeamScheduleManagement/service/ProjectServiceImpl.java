package com.yuhan.TeamScheduleManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.yuhan.TeamScheduleManagement.domain.Project;
import com.yuhan.TeamScheduleManagement.domain.Project.ProjectState;
import com.yuhan.TeamScheduleManagement.persistance.ProjectRepository;

import jakarta.transaction.Transactional;

@Service
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	ProjectRepository projectRepo;

	@Override
	public void projectRegister(List<String> techStackList, Project project) {
		project.setTechStacks(techStackList);

	    projectRepo.save(project);
	    
	    if (!techStackList.isEmpty()) {
	        String techStacksString = String.join(", ", techStackList);
	        System.out.println("Selected techStacks: " + techStacksString);
	    } else {
	        System.out.println("No techStacks selected.");
	    }
	}

	@Override
    public Page<Project> getProjectState(ProjectState state, int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        return projectRepo.findByProjectState(state, paging);
    }

    @Override
    public Page<Project> getAllProjects(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        return projectRepo.findAll(paging);
    }

	@Override
	public Page<Project> searchProjects(String projectName, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
	    return projectRepo.findByProjectNameContainingIgnoreCase(projectName, pageable);
	}
	
	@Override
	public Optional<Project> getProject(int projectId) {
		return projectRepo.findById(projectId);
	}

	@Override
	public Project getAviableProject(String userId) {
		ProjectState checkStateValue = ProjectState.BEFORE;
		Project avaiableProject = projectRepo.findByProjectLeaderAndProjectState(userId, checkStateValue);
		System.out.println("avaiableProject : " + avaiableProject);
		return avaiableProject;
	}
	
    @Transactional
    @Override
    public void incrementNumberOfPeople(String projectLeader) {
        projectRepo.incrementNumberOfPeople(projectLeader);
    }
}
