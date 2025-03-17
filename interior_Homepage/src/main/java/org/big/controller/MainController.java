package org.big.controller;

import java.util.List;

import org.big.dto.BoardDto;
import org.big.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	@Autowired
    private BoardService BoardService;

    @GetMapping("/")
    public String home() {
        return "thymeleaf/index"; // templates/index.html 로 연결
    }

    @GetMapping("/review")
    public String review() {
        return "thymeleaf/review"; // templates/review.html
    }
    
    @RequestMapping("/estimate")
    public ModelAndView board()throws Exception{
      ModelAndView mv = new ModelAndView("thymeleaf/estimate");
      
      List<BoardDto> list = BoardService.selectBoardList();
      mv.addObject("list",list);
      
      return mv;
   }

    @GetMapping("/cases")
    public String cases() {
        return "thymeleaf/cases"; // templates/cases.html
    }
}
