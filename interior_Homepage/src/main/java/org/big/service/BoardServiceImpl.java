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

    private final BoardMapper boardMapper;

    @Autowired
    public BoardServiceImpl(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    @Override
    public List<BoardDto> selectBoardList() throws Exception {
        System.out.println("게시글 목록: " + boardMapper.selectBoardList());
        return boardMapper.selectBoardList();
    }

    @Override
    public void insertBoard(BoardDto board) throws Exception {
        boardMapper.insertBoard(board);
    }

    @Override
    public BoardDto getBoardById(int iestiId) {
        return boardMapper.findById(iestiId);
    }

    @Override
    public void updateBoard(BoardDto boardDto) {
        boardMapper.update(boardDto);
    }

    @Override
    public void deleteBoard(int iestiId) {
        boardMapper.delete(iestiId);
    }
}
