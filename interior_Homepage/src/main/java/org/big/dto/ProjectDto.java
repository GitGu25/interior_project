package org.big.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class ProjectDto {
	private int iprojectId;             // 시공 프로젝트 고유 ID
    private int iprojectUserId;         // 업자 ID (FK)
    private String iprojectName;        // 작성자 이름
    private String iprojectTitle;       // 시공 사례 제목
    private String iprojectDescription; // 시공 사례 설명 (CLOB)
    private String iprojectImageUrl;   // 대표 이미지 URL (추가됨)
    private LocalDateTime iprojectCreatedAt;// 시공 사례 업로드 일시
    private List<PhotoDto> photos; // 관련 이미지 리스트
}
