package com.yuhan.TeamScheduleManagement.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.yuhan.TeamScheduleManagement.domain.Project;
import com.yuhan.TeamScheduleManagement.domain.Project.ProjectState;

public interface ProjectService {
	// 프로젝트 등록
    void projectRegister(List<String> techStackList, Project project);

    // 특정 상태의 프로젝트를 페이징 처리하여 조회
    Page<Project> getProjectState(ProjectState state, int page, int size);

    // 모든 프로젝트를 페이징 처리하여 조회
    Page<Project> getAllProjects(int page, int size);
}
