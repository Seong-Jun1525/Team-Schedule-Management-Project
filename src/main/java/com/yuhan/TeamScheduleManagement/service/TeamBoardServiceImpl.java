package com.yuhan.TeamScheduleManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.yuhan.TeamScheduleManagement.domain.TeamBoard;
import com.yuhan.TeamScheduleManagement.persistance.TeamBoardRepository;

import jakarta.transaction.Transactional;

@Service
public class TeamBoardServiceImpl implements TeamBoardService {

	@Autowired
	TeamBoardRepository teamBoardRepo;
	
	@Override
	public void teamBoardInsert(TeamBoard teamBoard, String memberId) {
		teamBoard.setTeamBoardMemberId(memberId);
		teamBoardRepo.save(teamBoard);
	}

	@Override
	public Page<TeamBoard> getAllTeamBoards(int page, int size) {
		Pageable paging = PageRequest.of(page, size);
		return teamBoardRepo.findAll(paging);
	}

	@Override
	public Page<TeamBoard> searchTeamBoards(String teamBoardTitle, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TeamBoard getTeamBoard(int teamBoardId) {
		return teamBoardRepo.findById(teamBoardId).orElseThrow();
	}

	@Transactional
	public void teamBoardUpdate(TeamBoard teamBoard) {
		TeamBoard existingTeamBoard = getTeamBoard(teamBoard.getTeamBoardId());
		existingTeamBoard.setTeamBoardTitle(teamBoard.getTeamBoardTitle());
		existingTeamBoard.setTeamBoardContent(teamBoard.getTeamBoardContent());
		existingTeamBoard.setCreatedAt(teamBoard.getCreatedAt());
		teamBoardRepo.save(existingTeamBoard);
	}

	@Override
	public void teamBoardDelete(int teamBoardId) {
		teamBoardRepo.deleteById(teamBoardId);
	}
}
