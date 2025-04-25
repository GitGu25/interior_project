package org.big.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ReviewDto {

	private long ireviewId; // 리뷰 번호
	private String ireviewName; // 작성자 이름
	private String ireviewPhone; // 게시글 전화번호(확인용)
	private String ireviewTitle; // 제목
	private String ireviewText; // 후기 내용
	private LocalDateTime ireviewCreatedAt; // 작성일
	private String ireviewTypes;  // 새로 추가된 필드
	
    private List<PhotoDto> photos;	// DB에서 가져오는 이미지 정보
    private List<MultipartFile> uploadFiles; // 업로드할 파일. 이미지 파일 받기용 필드 (폼에서 넘어오는 MultipartFile)
    
    // 대표 이미지 URL (DB에는 없음)
    private String thumbUrl;
}