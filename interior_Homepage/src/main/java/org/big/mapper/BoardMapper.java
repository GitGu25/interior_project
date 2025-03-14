package org.big.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.big.dto.BoardDto;

@Mapper
public interface BoardMapper {
	
	List<BoardDto> selectBoardList() throws Exception;
	
}
