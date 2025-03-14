package org.big.controller;

import java.util.List;

import org.big.dto.BoardDto;
import org.big.mapper.BoardMapper;
import org.big.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/estimate-requests")
public class EstimateRequestController {

    @Autowired
    private BoardService BoardService;
    
    @Autowired
    private BoardMapper BoardMapper;

//    @RequestMapping("/estimate")
//    public ModelAndView board()throws Exception{
//      ModelAndView mv = new ModelAndView("estimate");
//      
//      List<BoardDto> list = BoardService.selectBoardList();
//      mv.addObject("list",list);
//      
//      return mv;
//   }
}
