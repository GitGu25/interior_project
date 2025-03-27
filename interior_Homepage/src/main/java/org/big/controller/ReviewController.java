package org.big.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.big.dto.ReviewDto;
import org.big.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    
    

    // 📌 리뷰 목록 (페이지네이션 가능)
    @GetMapping("/list")
    public String listReviews(Model model,
                              @RequestParam(defaultValue = "1") int page,   // 페이지 번호 (기본값: 1)
                              @RequestParam(defaultValue = "10") int size) { // 페이지당 항목 수 (기본값: 10)
        try {
            // 리뷰 목록을 페이지네이션으로 가져오는 서비스 메소드
            List<ReviewDto> reviews = reviewService.getReviews(page, size);
            
            // 총 페이지 수를 계산하는 서비스 메소드
            int totalPages = reviewService.getTotalPages(size);

            // 모델에 데이터를 담아서 뷰로 전달
            model.addAttribute("reviews", reviews);   // 리뷰 목록
            model.addAttribute("currentPage", page);   // 현재 페이지 번호
            model.addAttribute("totalPages", totalPages); // 총 페이지 수
            model.addAttribute("size", size);          // 페이지당 항목 수
            model.addAttribute("bannerTitle", "고객 후기");
            model.addAttribute("bannerDescription", "시공 후기 게시판");
        } catch (Exception e) {
            e.printStackTrace();  // 예외 처리
        }

        return "thymeleaf/review"; // 반환할 뷰 이름
    }



    // 📌 리뷰 작성 페이지
    @GetMapping("/write")
    public String openReviewWrite() {
        return "thymeleaf/reviewWrite"; // 리뷰 작성 페이지로 이동
    }

    // 📌 리뷰 작성 처리
    @PostMapping("/write")
    public String submitReview(ReviewDto reviewDto) throws Exception {
        reviewService.insertReview(reviewDto); // 리뷰 저장
        return "redirect:/review/list"; // 작성 후 리뷰 목록 페이지로 리다이렉트
    }

    // 📌 특정 리뷰 조회
    @GetMapping("/view/{reviewId}")
    public String getReviewDetail(@PathVariable int reviewId, Model model) throws Exception {
        ReviewDto review = reviewService.getReviewById(reviewId);
        if (review == null) {
            return "error/404";  // 없는 리뷰라면 404 페이지로 이동
        }
        model.addAttribute("review", review);
        return "thymeleaf/reviewDetail"; // 리뷰 상세 페이지로 이동
    }

    // 📌 리뷰 수정 페이지
    @GetMapping("/update/{reviewId}")
    public String openReviewUpdate(@PathVariable int reviewId, Model model) throws Exception {
        ReviewDto review = reviewService.getReviewById(reviewId);
        if (review == null) {
            return "error/404"; // 없는 리뷰라면 404 페이지로 이동
        }
        model.addAttribute("review", review);
        return "thymeleaf/reviewUpdate"; // 리뷰 수정 페이지로 이동
    }

    // 📌 리뷰 수정 처리
    @PostMapping("/update")
    public String updateReview(@ModelAttribute ReviewDto reviewDto) throws Exception {
        reviewService.updateReview(reviewDto); // 리뷰 수정
        return "redirect:/review/list"; // 수정 후 리뷰 목록 페이지로 리다이렉트
    }

    // 📌 리뷰 삭제 처리
    @PostMapping("/delete")
    public String deleteReview(@RequestParam("reviewId") int reviewId) throws Exception {
        reviewService.deleteReview(reviewId); // 리뷰 삭제
        return "redirect:/review/list"; // 삭제 후 리뷰 목록 페이지로 리다이렉트
    }
}
