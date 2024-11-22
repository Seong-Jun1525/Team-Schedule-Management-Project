package com.yuhan.TeamScheduleManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuhan.TeamScheduleManagement.domain.TechStack;
import com.yuhan.TeamScheduleManagement.persistance.TechStackRepository;

@Service
public class TechStackServiceImpl implements TechStackService {

	@Autowired
	private TechStackRepository techStackRepo;
	
	@Override
	public List<TechStack> getAllTechStacks() {
		return techStackRepo.findAll();
	}

}
