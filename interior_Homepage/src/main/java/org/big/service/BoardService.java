package org.big.service;

import java.util.List;

import org.big.dto.BoardDto;

public interface BoardService {

	List<BoardDto> selectBoardList() throws Exception;
}
