package org.big.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BoardDto {

	private int interiorEstimateId;	// 게시글 번호
	private String interiorEstimateName; // 작성자 이름
	private String interiorEstimatePassword; // 비번
	private String interiorEstimateRequestText; // 내용
	private InteriorEstimateStatus interiorEstimateStatus; // 상태 (Enum 적용)
	private LocalDateTime interiorEstimateCreatedAt; // 작성일
	private String interiorEstimateTitle; // 제목
}
