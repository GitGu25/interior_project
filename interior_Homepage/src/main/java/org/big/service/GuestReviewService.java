//package org.big.service;
//
//import org.big.domain.GuestReview;
//import org.big.repository.GuestReviewRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class GuestReviewService {
//
//    @Autowired
//    private GuestReviewRepository guestReviewRepository;
//
//    // 모든 고객 후기 가져오기
//    public List<GuestReview> getAllReviews() {
//        return guestReviewRepository.findAll();
//    }
//
//    // ID로 고객 후기 가져오기
//    public Optional<GuestReview> getReviewById(Long id) {
//        return guestReviewRepository.findById(id);
//    }
//
//    // 고객 후기 저장하기
//    public GuestReview saveReview(GuestReview guestReview) {
//        return guestReviewRepository.save(guestReview);
//    }
//
//    // 고객 후기 삭제하기
//    public void deleteReview(Long id) {
//        guestReviewRepository.deleteById(id);
//    }
//}
