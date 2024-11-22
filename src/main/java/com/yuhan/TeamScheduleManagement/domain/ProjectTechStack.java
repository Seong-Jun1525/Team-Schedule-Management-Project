package com.yuhan.TeamScheduleManagement.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class ProjectTechStack {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project; // 해당 기술 스택이 속하는 프로젝트

    @Column(nullable = false, length = 50)
    private String techStackName;
}
