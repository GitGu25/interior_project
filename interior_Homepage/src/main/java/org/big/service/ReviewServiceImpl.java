package org.big.service;

import java.util.List;

import org.big.dto.ReviewDto;
import org.big.mapper.ReviewMapper; // ReviewMapper 사용
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

    @Override
    public List<ReviewDto> selectReviewList() throws Exception {
        // 리뷰 목록 조회 로직
        return reviewMapper.selectReviewList();
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
