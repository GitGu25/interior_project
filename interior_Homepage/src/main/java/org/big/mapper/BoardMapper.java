package org.big.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.big.dto.BoardDto;

@Mapper
public interface BoardMapper {

    // 게시글 목록 조회
    List<BoardDto> selectBoardList();

    // 게시글 추가
    void insertBoard(BoardDto board) throws Exception;
}
