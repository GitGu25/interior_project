package org.big.controller;

import java.util.List;

import org.big.dto.ProjectDto;
import org.big.dto.ReviewDto;
import org.big.service.ProjectService;
import org.big.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    private ReviewService reviewService;
    
    @Autowired
    private ProjectService projectService;
    
    @GetMapping("/")
    public String home(Model model) {
        // 🔽 최신 리뷰 3개 가져오기
        List<ReviewDto> latestReviews = reviewService.getLatestReviews();
        model.addAttribute("latestReviews", latestReviews);

        // 🔽 최신 프로젝트 2개 가져오기
        List<ProjectDto> latestProjects = projectService.getLatestProjects();
        model.addAttribute("latestprojects", latestProjects);

        return "thymeleaf/index";
    }

    @GetMapping("/intro")
    public String intro(Model model) {
        model.addAttribute("bannerTitle", "회사 소개");
        model.addAttribute("bannerDescription", "화목 홈 인테리어");
        
        return "thymeleaf/intro"; // templates/intro.html
    }

    @GetMapping("/projects")
    public String redirectToProjects() {
        return "redirect:/project/list"; // 프로젝트 목록을 처리하는 컨트롤러로 리다이렉트
    }
    
    @GetMapping("/estimate")
    public String boardList(Model model) {
        model.addAttribute("bannerTitle", "견적 문의");
        model.addAttribute("bannerDescription", "견적 문의 게시판");

        return "redirect:/estimate/list";
    }
    
    @GetMapping("/review")
    public String redirectToReview() {
        return "redirect:/review/list"; // 리뷰 목록을 처리하는 컨트롤러로 리다이렉트
    }
    
}
