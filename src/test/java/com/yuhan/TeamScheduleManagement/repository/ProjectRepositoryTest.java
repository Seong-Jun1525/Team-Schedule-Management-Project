package com.yuhan.TeamScheduleManagement.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yuhan.TeamScheduleManagement.domain.Project;
import com.yuhan.TeamScheduleManagement.domain.Project.ProjectState;
import com.yuhan.TeamScheduleManagement.persistance.ProjectRepository;

@SpringBootTest
public class ProjectRepositoryTest {

	@Autowired
	private ProjectRepository projectRepo;
	
//	@Test
	public void insertProject() {
		Project project = new Project();
		project.setProjectName("Test SeongJun Project");
		project.setProjectLeader("SeongJun");
		project.setNumberOfPeople(3);
		project.setProjectContent("TestTestTest");
		project.setProjectGoal("Test Goal");
		project.setProjectPeriod("24-11-01 ~ 24-12-01");
		project.setProjectState(ProjectState.BEFORE);

		projectRepo.save(project);
	}
}
