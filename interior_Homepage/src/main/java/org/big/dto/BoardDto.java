package org.big.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BoardDto {

	private int iestiId;	// 게시글 번호
	private String iestiName; // 작성자 이름
	private String iestiPhone; // 전화번호
	private String iestiTitle; // 제목
	private String iestiRequestText; // 내용
	private String iestiStatus; // 상태
	private LocalDateTime iestiCreatedAt; // 작성일
	
}
