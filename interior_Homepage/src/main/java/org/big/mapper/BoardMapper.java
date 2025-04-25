package org.big.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.big.dto.BoardDto;

@Mapper
public interface BoardMapper {

	// 페이지네이션 적용된 게시글 목록 조회
    List<BoardDto> getBoardList(@Param("offset") int offset, @Param("size") int size) throws Exception;  // 페이지네이션을 위한 게시글 조회
    int getTotalBoardCount() throws Exception;
    void insertBoard(BoardDto board) throws Exception;	// 게시글 추가
    BoardDto findById(int iestiId);   // 게시글 상세 조회
    void update(BoardDto board); // 게시글 수정
    void delete(int iestiId);         // 게시글 삭제
}
