package org.big.service;

import java.util.List;

import org.big.dto.ReviewDto;

public interface ReviewService {

	// 리뷰 목록 조회
	List<ReviewDto> getReviews(int page, int size) throws Exception;

	// 총 페이지 개수 계산
	// getTotalPages(int size)는 서비스에서만 계산하도록 유지
	int getTotalPages(int size) throws Exception;

	ReviewDto getReviewById(Long ireviewId) throws Exception; // 특정 리뷰 조회

	void updateReview(ReviewDto reviewDto) throws Exception; // 리뷰 수정

	void deleteReview(Long ireviewId) throws Exception; // 리뷰 삭제

	// ✅ 리뷰 + 사진 업로드 묶어서 저장
	void reviewBundle(ReviewDto reviewDto) throws Exception;

	List<ReviewDto> getLatestReviews();

	void updateRBundle(ReviewDto reviewDto, List<Long> deletePhotoIds) throws Exception;

	boolean verifyPhoneNumber(Long ireviewId, String inputPhone) throws Exception;

}
