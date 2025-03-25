package org.big.service;

import org.big.dto.ReviewDto;
import java.util.List;

public interface ReviewService {

    
    List<ReviewDto> selectReviewList() throws Exception; // 리뷰 목록 조회
    void insertReview(ReviewDto reviewDto) throws Exception; // 리뷰 추가
    ReviewDto getReviewById(int reviewId) throws Exception;	// 특정 리뷰 조회
    void updateReview(ReviewDto reviewDto) throws Exception; // 리뷰 수정
    void deleteReview(int reviewId) throws Exception; // 리뷰 삭제
}
