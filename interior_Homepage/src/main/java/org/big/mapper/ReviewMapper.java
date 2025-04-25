package org.big.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.big.dto.ReviewDto;

@Mapper
public interface ReviewMapper {

	// 리뷰 목록 조회 (페이지네이션 처리)
    List<ReviewDto> getReviews(@Param("offset") int offset, @Param("size") int size) throws Exception;

    // 전체 리뷰 개수 조회 (총 페이지 수 계산 X)
    //ReviewMapper는 오직 "DB에서 데이터를 가져오는 역할"만 수행해야 하기 때문에 getTotalPages(int size)는 DB에서 직접 조회할 수 있는 값이 아님
    int getTotalReviewCount() throws Exception;

	void insertReview(ReviewDto reviewDto) throws Exception; // 리뷰 추가

	ReviewDto getReviewById(Long ireviewId) throws Exception; // 특정 리뷰 조회

	void updateReview(ReviewDto reviewDto) throws Exception; // 리뷰 수정

	void deleteReview(Long ireviewId) throws Exception; // 리뷰 삭제

	List<ReviewDto> selectLatestReviews();	//메인에 리뷰가져오기
}