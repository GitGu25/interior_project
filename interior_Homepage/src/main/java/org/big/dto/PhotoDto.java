package org.big.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PhotoDto {
	private Long iphotoId; // 사진 고유 ID
	private String iphotoFilename; // 저장된 파일 이름
	private String iphotoExtension; // 파일 확장자 (JPG, PNG, GIF 등)
	private LocalDateTime iphotoUploadedAt; // 업로드 날짜
	private Long projectId; // 시공 사례 ID (NULL 가능)
	private Long reviewId; // 리뷰 ID (NULL 가능)

	public String getIphotoUrl() {
        return "/uploads/" + iphotoFilename;
    }
}
