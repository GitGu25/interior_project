package org.big.controller;

import org.big.dto.BoardDto;
import org.big.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
        return "redirect:/estimate";  // 게시글 목록 페이지로 리다이렉트  
    }
    
    @GetMapping("/estimate/view/{iestiId}")
    public String getBoardDetail(@PathVariable int iestiId, Model model) {
        BoardDto board = boardService.getBoardById(iestiId);
        
        if (board == null) {
            return "error/404";  // 404 페이지로 이동
        }

        model.addAttribute("board", board);
        return "thymeleaf/estimateDetail";  
    }

    // 게시글 수정 (PUT)
    @RequestMapping(value = "/estimate/update", method = RequestMethod.POST)
    public String updateBoard(@ModelAttribute BoardDto boardDto) {
        boardService.updateBoard(boardDto);
        return "redirect:/estimate";  // 수정 후 상세 페이지로 이동
    }

    // 게시글 삭제 (DELETE)
    @RequestMapping(value = "/estimate/delete", method = RequestMethod.POST)
    public String deleteBoard(@RequestParam("iestiId") int iestiId) {
        boardService.deleteBoard(iestiId);
        return "redirect:/estimate";  // 삭제 후 목록으로 이동
    }

}