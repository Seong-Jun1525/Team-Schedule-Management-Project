package com.yuhan.TeamScheduleManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.yuhan.TeamScheduleManagement.domain.Project;
import com.yuhan.TeamScheduleManagement.domain.Project.ProjectState;

public interface ProjectService {
	// 프로젝트 등록
    public void projectRegister(List<String> techStackList, Project project);

    // 특정 상태의 프로젝트를 페이징 처리하여 조회
    public Page<Project> getProjectState(ProjectState state, int page, int size);

    // 모든 프로젝트를 페이징 처리하여 조회
    public Page<Project> getAllProjects(int page, int size);
    
    // 프로젝트 검색
    public Page<Project> searchProjects(String projectName, int page, int size);

	// 해당 프로젝트 가져오기
	public Optional<Project> getProject(int projectId);
}
