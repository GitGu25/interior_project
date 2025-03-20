package org.big.service;

import java.util.List;

import org.big.dto.BoardDto;

public interface BoardService {

	List<BoardDto> selectBoardList() throws Exception;// 게시글 목록 조회
	void insertBoard(BoardDto board) throws Exception; // 게시글 추가
}
