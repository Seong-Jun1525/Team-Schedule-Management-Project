package com.yuhan.TeamScheduleManagement.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tech_stack")
public class TechStack {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int techStackId;

    @Column(nullable = false, length = 50)
    private String techStackName;

    @Column(length = 50)
    private String techStackType;
}