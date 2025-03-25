package org.big.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.big.dto.ReviewDto;

@Mapper
public interface ReviewMapper {

	List<ReviewDto> selectReviewList() throws Exception; // 리뷰 목록 조회
    void insertReview(ReviewDto reviewDto) throws Exception; // 리뷰 추가
    ReviewDto getReviewById(int reviewId) throws Exception;	// 특정 리뷰 조회
    void updateReview(ReviewDto reviewDto) throws Exception; // 리뷰 수정
    void deleteReview(int reviewId) throws Exception; // 리뷰 삭제
}
