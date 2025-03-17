package org.big.controller;

import org.big.dto.BoardDto;
import org.big.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EstimateRequestController {

    @Autowired
    private BoardService boardService;

    // GET 요청으로 작성 페이지로 이동
    @GetMapping("/estimate/write")
    public String openBoardWrite() throws Exception {
        return "thymeleaf/estimateWrite";  // 작성 화면 페이지
    }

 // POST 요청으로 게시글 작성
    @PostMapping("/estimate/write")
    public String insertBoard(BoardDto board) throws Exception {
        boardService.insertBoard(board);  // 게시글 저장
        return "redirect:/estimate-requests/estimate";  // 게시글 목록 페이지로 리다이렉트  
    }
}