package org.big.service;

import java.io.IOException;
import java.util.List;

import org.big.dto.PhotoDto;
import org.springframework.web.multipart.MultipartFile;

public interface PhotoService {
    
    void insertPhoto(PhotoDto photoDto); // 사진 저장
    List<PhotoDto> getPhotos(int reviewId); // 특정 리뷰에 연결된 사진 목록 조회
    void deletePhotos(int reviewId);  // 특정 리뷰에 연결된 사진 삭제
    String savePhoto(MultipartFile file) throws IOException;
}
