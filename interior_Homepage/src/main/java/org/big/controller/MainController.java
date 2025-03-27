package org.big.controller;

import java.util.List;
import org.big.dto.BoardDto;
import org.big.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    private BoardService BoardService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("bannerTitle", "메인 페이지");
        model.addAttribute("bannerDescription", "우리 홈페이지에 오신 것을 환영합니다!");
        return "thymeleaf/index"; // templates/index.html 로 연결
    }

    @GetMapping("/intro")
    public String intro(Model model) {
        model.addAttribute("bannerTitle", "회사 소개");
        model.addAttribute("bannerDescription", "화목 홈 인테리어");
        return "thymeleaf/intro"; // templates/intro.html
    }

    @GetMapping("/cases")
    public String cases(Model model) {
        model.addAttribute("bannerTitle", "시공 사례");
        model.addAttribute("bannerDescription", "시공사례 및 작업현장");
        return "thymeleaf/cases"; // templates/cases.html
    }
    
    @GetMapping("/estimate")
    public String boardList(Model model) {
        model.addAttribute("bannerTitle", "견적 문의");
        model.addAttribute("bannerDescription", "견적 문의 게시판");

        try {
            List<BoardDto> list = BoardService.selectBoardList();
            model.addAttribute("list", list);
            System.out.println("pppppppppppppppppppppppppppppppp" + list.toString());
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "목록을 불러오는 중 오류가 발생했습니다.");
        }

        return "thymeleaf/estimate";
    }
    
    @GetMapping("/review")
    public String redirectToReview() {
        return "redirect:/review/list"; // 리뷰 목록을 처리하는 컨트롤러로 리다이렉트
    }
    
}
