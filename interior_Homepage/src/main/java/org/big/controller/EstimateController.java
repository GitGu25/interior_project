package org.big.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.big.dto.BoardDto;
import org.big.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EstimateController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/estimate/list")
    public String boardList(Model model,
                            @RequestParam(defaultValue = "1") int page,  // 페이지 번호 (기본값: 1)
                            @RequestParam(defaultValue = "10") int size) { // 페이지당 항목 수 (기본값: 10)
        try {
            // 게시글 목록을 페이지네이션으로 가져오는 서비스 메소드
            List<BoardDto> list = boardService.getBoardList(page, size);  // 페이지와 size를 전달하여 목록 가져오기

            // 총 페이지 수를 계산하는 서비스 메소드
            int totalPages = boardService.getTotalPages(size);  // 페이지당 항목 수를 이용하여 총 페이지 수 계산

            // 모델에 데이터를 담아서 뷰로 전달
            model.addAttribute("list", list);  // 게시글 목록
            model.addAttribute("currentPage", page); // 현재 페이지 번호
            model.addAttribute("totalPages", totalPages); // 총 페이지 수
            model.addAttribute("size", size); // 페이지당 항목 수
            model.addAttribute("bannerTitle", "견적 문의");
            model.addAttribute("bannerDescription", "견적 문의 게시판");

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "목록을 불러오는 중 오류가 발생했습니다.");
        }

        return "thymeleaf/estimate";  // 목록 페이지로 이동
    }

    
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

    //견적문의 전화번호 확인
    @PostMapping("/estimate/verify")
    @ResponseBody
    public Map<String, Object> verifyPhoneNumber(@RequestBody Map<String, Object> requestData) {
        int iestiId = Integer.parseInt(requestData.get("iestiId").toString());
        String inputPhone = requestData.get("phone").toString();

        boolean match = boardService.verifyPhoneNumber(iestiId, inputPhone);

        Map<String, Object> response = new HashMap<>();
        response.put("success", match);
        return response;
    }

}