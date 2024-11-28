package com.yuhan.TeamScheduleManagement.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.yuhan.TeamScheduleManagement.domain.TeamBoard;

public interface TeamBoardService {
	// 글 등록
	public void teamBoardInsert(TeamBoard teamBoard, String memberId);
	
	// 모든 글 조회
	public Page<TeamBoard> getAllTeamBoards(int page, int size);

	// 검색을 통해서 글 조회
	public Page<TeamBoard> searchTeamBoards(String teamBoardTitle, int page, int size);

	public TeamBoard getTeamBoard(int teamBoardId);

	public void teamBoardUpdate(TeamBoard teamBoard);

	public void teamBoardDelete(int teamBoardId);
}
