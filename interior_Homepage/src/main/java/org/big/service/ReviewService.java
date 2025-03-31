package org.big.service;

import java.util.List;

import org.big.dto.ReviewDto;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface ReviewService {

	 // 리뷰 목록 조회
    List<ReviewDto> getReviews(int page, int size) throws Exception;

    // 총 페이지 개수 계산
    //getTotalPages(int size)는 서비스에서만 계산하도록 유지
    int getTotalPages(int size) throws Exception;

	void insertReview(ReviewDto reviewDto, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception; // 리뷰 추가

	ReviewDto getReviewById(int reviewId) throws Exception; // 특정 리뷰 조회

	void updateReview(ReviewDto reviewDto) throws Exception; // 리뷰 수정

	void deleteReview(int reviewId) throws Exception; // 리뷰 삭제

}
