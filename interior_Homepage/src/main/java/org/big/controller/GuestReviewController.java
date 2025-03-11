//package org.big.controller;
//
//import org.big.domain.GuestReview;
//import org.big.service.GuestReviewService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/guest-reviews")
//public class GuestReviewController {
//
//    @Autowired
//    private GuestReviewService guestReviewService;
//
//    // 고객 후기 목록 가져오기
//    @GetMapping
//    public List<GuestReview> getAllReviews() {
//        return guestReviewService.getAllReviews();
//    }
//
//    // 고객 후기 ID로 가져오기
//    @GetMapping("/{id}")
//    public Optional<GuestReview> getReviewById(@PathVariable Long id) {
//        return guestReviewService.getReviewById(id);
//    }
//
//    // 고객 후기 저장하기
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public GuestReview createReview(@RequestBody GuestReview guestReview) {
//        return guestReviewService.saveReview(guestReview);
//    }
//
//    // 고객 후기 삭제하기
//    @DeleteMapping("/{id}")
//    public void deleteReview(@PathVariable Long id) {
//        guestReviewService.deleteReview(id);
//    }
//}
