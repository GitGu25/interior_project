package org.big.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ReviewDto {

	private int ireviewId; // 리뷰 번호
	private String ireviewName; // 작성자 이름
	private String ireviewPassword; // 게시글 비밀번호
	private String ireviewTitle; // 제목
	private String ireviewText; // 후기 내용
	private LocalDateTime ireviewCreatedAt; // 작성일
}