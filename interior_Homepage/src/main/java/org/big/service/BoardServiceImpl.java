package org.big.service;

import java.util.List;

import org.big.dto.BoardDto;
import org.big.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class BoardServiceImpl implements BoardService {
    
    @Autowired
    private BoardMapper boardMapper;
    
    @Override
    public List<BoardDto> selectBoardList() throws Exception {
        // 게시글 목록 조회
        return boardMapper.selectBoardList();
    }
    
    @Override
    public void insertBoard(BoardDto board) throws Exception {
        // 게시글 삽입
        boardMapper.insertBoard(board);
    }
}