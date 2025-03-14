package org.big.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class BoardDto {

	private int interiorEstimateId;	//게시글 번호
	private String interiorEstimateName;//작성자이름
	private String interiorEstimatePassword;//비번
	private String interiorEstimateRequestText;//내용
	private String interiorEstimateStatus;//상태
	private LocalDateTime interiorEstimateCreatedAt;//작성일
	private String interiorEstimateTitle;//제목
}
