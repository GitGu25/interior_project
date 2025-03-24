package org.big.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.big.dto.BoardDto;

@Mapper
public interface BoardMapper {

    List<BoardDto> selectBoardList() throws Exception;	// 게시글 목록 조회
    void insertBoard(BoardDto board) throws Exception;	// 게시글 추가
    BoardDto findById(int iestiId);   // 게시글 상세 조회
    void update(BoardDto board); // 게시글 수정
    void delete(int iestiId);         // 게시글 삭제
}
