package org.big.service;

import java.util.List;

import org.big.dto.ReviewDto;
import org.big.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

	private final ReviewMapper reviewMapper;

	@Autowired
	public ReviewServiceImpl(ReviewMapper reviewMapper) {
		this.reviewMapper = reviewMapper;
	}

	// 리뷰 목록 조회 (페이지네이션 적용)
	@Override
	public List<ReviewDto> getReviews(int page, int size) throws Exception {
		int offset = (page - 1) * size; // offset 계산
		return reviewMapper.getReviews(offset, size);
	}

	// 전체 리뷰 개수 조회 (페이지네이션을 위한 총 개수)
	public int getTotalReviewCount() throws Exception {
		return reviewMapper.getTotalReviewCount(); // ReviewMapper에서 getTotalReviewCount() 호출
	}

	// 전체 페이지 수 계산 (size와 리뷰 개수 기반)
	public int getTotalPages(int size) throws Exception {
		int totalReviews = getTotalReviewCount(); // 리뷰 개수 가져오기
		return (int) Math.ceil((double) totalReviews / size); // 페이지 수 계산
	}

	@Override
	public void insertReview(ReviewDto reviewDto) throws Exception {
		// 리뷰 추가 로직
		reviewMapper.insertReview(reviewDto);
	}

	@Override
	public ReviewDto getReviewById(int reviewId) throws Exception {
		// 특정 리뷰 조회 로직
		return reviewMapper.getReviewById(reviewId);
	}

	@Override
	public void updateReview(ReviewDto reviewDto) throws Exception {
		// 리뷰 수정 로직
		reviewMapper.updateReview(reviewDto);
	}

	@Override
	public void deleteReview(int reviewId) throws Exception {
		// 리뷰 삭제 로직
		reviewMapper.deleteReview(reviewId);
	}

}
