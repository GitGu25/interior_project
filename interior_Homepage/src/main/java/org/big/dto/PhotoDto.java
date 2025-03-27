package org.big.dto;

import lombok.Data;

@Data
public class PhotoDto {
    private int iphotoId;         // 사진 고유 ID
    private int iphotoProjectId;   // 프로젝트 ID (외래 키)
    private int iphotoReviewId;   // 리뷰 ID (외래 키)
    private String iphotoUrl;     // 사진 URL
    private String iphotoDescription;  // 사진 설명
}
