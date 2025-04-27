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

    // 페이지네이션을 적용한 게시글 목록 조회
    @Override
    public List<BoardDto> getBoardList(int page, int size) throws Exception {
        int offset = (page - 1) * size; // 페이지 오프셋 계산
        return boardMapper.getBoardList(offset, size);
    }

    // 전체 게시글 개수 조회 (페이지네이션용)
    public int getTotalBoardCount() throws Exception {
        return boardMapper.getTotalBoardCount();
    }

    // 전체 페이지 수 계산
    @Override
    public int getTotalPages(int size) throws Exception {
        int totalBoards = getTotalBoardCount(); // 전체 게시글 수
        return (int) Math.ceil((double) totalBoards / size); // 페이지 수 계산
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
    
    //전화번호 확인 기능
    @Override
    public boolean verifyPhoneNumber(int iestiId, String inputPhone) {
        BoardDto board = getBoardById(iestiId); // 게시글 정보 조회
        if (board == null || board.getIestiPhone() == null) {
            return false;
        }

        // 숫자만 남겨서 비교 (공백, 하이픈 등 제거)
        String savedPhone = board.getIestiPhone().replaceAll("[^0-9]", "");
        String enteredPhone = inputPhone.replaceAll("[^0-9]", "");

        return savedPhone.equals(enteredPhone); // 전화번호 일치 여부 반환
    }

}
