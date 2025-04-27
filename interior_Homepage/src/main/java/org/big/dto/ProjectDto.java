package org.big.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProjectDto {
	private long iprojectId; // 시공 프로젝트 번호
	/* private long iprojectUserId; */  //시공 사례 관리자 ID (FK)
    private String iprojectName; // 작성자 이름
    private String iprojectTitle; // 제목
    private String iprojectText; // 시공 사례 설명 (CLOB)
    private LocalDateTime iprojectCreatedAt;// 시공 사례 업로드 일시
 
    private List<PhotoDto> photos; // // DB에서 가져오는 이미지 정보
    private List<MultipartFile> uploadFiles;
    // 업로드할 파일. 이미지 파일 받기용 필드 (폼에서 넘어오는 MultipartFile)
    
    // 대표 이미지 URL (DB에는 없음)
    private String thumbUrl;
}
