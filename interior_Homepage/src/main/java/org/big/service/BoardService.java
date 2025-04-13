package org.big.service;

import java.util.List;

import org.big.dto.BoardDto;

public interface BoardService {

	List<BoardDto> getBoardList(int page, int size) throws Exception;// 게시글 목록 조회
	int getTotalPages(int size) throws Exception;
	void insertBoard(BoardDto board) throws Exception; // 게시글 추가
	BoardDto getBoardById(int iestiId);
	void updateBoard(BoardDto boardDto);
	void deleteBoard(int iestiId);
	
}
