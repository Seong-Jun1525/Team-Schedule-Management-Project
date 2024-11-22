package com.yuhan.TeamScheduleManagement.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Project {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectId;

    @Column(nullable = false, length = 50, unique = true)
    private String projectName;

    @Column(nullable = false, length = 20)
    private String projectLeader; // 프로젝트 리더 ID

    @Column(nullable = false)
    private int numberOfPeople = 1;

    @Column(nullable = false, length = 100)
    private String projectGoal;

    @Column(nullable = false, length = 255)
    private String projectContent;

    @Column(length = 100)
    private String projectPeriod; // "YY-MM-DD ~ YY-MM-DD" 형식

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private ProjectState projectState = ProjectState.BEFORE;

    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @ElementCollection
    @Column(name = "tech_stack_name")
    private List<String> techStacks = new ArrayList<>();

    public enum ProjectState {
        BEFORE, START, END, DELETE
    }
}
