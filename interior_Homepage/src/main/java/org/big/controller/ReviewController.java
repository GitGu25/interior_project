package org.big.controller;

import org.big.dto.ReviewDto;
import org.big.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // GET 요청으로 리뷰 작성 페이지로 이동
    @GetMapping("/review/write")
    public String openReviewWrite() {
        return "review/write";  // 작성 화면 페이지
    }

    // POST 요청으로 리뷰 작성
    @PostMapping("/review/write")
    public String submitReview(ReviewDto reviewDto) throws Exception {
        reviewService.insertReview(reviewDto);  // 리뷰 저장
        return "redirect:/review";  // 리뷰 목록 페이지로 리다이렉트
    }

    // 특정 리뷰 조회 페이지
    @GetMapping("/review/view/{reviewId}")
    public String getReviewDetail(@PathVariable int reviewId, Model model) throws Exception {
        ReviewDto review = reviewService.getReviewById(reviewId);

        if (review == null) {
            return "error/404";  // 404 페이지로 이동
        }

        model.addAttribute("review", review);
        return "review/detail";  // review 폴더의 detail.html 반환
    }

    // 리뷰 수정 페이지
    @GetMapping("/review/update/{reviewId}")
    public String openReviewUpdate(@PathVariable int reviewId, Model model) throws Exception {
        ReviewDto review = reviewService.getReviewById(reviewId);

        if (review == null) {
            return "error/404";  // 404 페이지로 이동
        }

        model.addAttribute("review", review);
        return "review/update";  // 리뷰 수정 화면 페이지
    }

    // 리뷰 수정 처리 (POST)
    @PostMapping("/review/update")
    public String updateReview(@ModelAttribute ReviewDto reviewDto) throws Exception {
        reviewService.updateReview(reviewDto);
        return "redirect:/review";  // 수정 후 리뷰 목록 페이지로 리다이렉트
    }

    // 리뷰 삭제 (POST)
    @PostMapping("/review/delete")
    public String deleteReview(@RequestParam("reviewId") int reviewId) throws Exception {
        reviewService.deleteReview(reviewId);
        return "redirect:/review";  // 삭제 후 리뷰 목록 페이지로 리다이렉트
    }
}
