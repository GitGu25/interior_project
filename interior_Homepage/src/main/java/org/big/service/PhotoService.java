package org.big.service;

import lombok.RequiredArgsConstructor;
import org.big.dto.PhotoDto;
import org.big.mapper.PhotoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoMapper photoMapper;

    // 사진 추가
    @Transactional
    public void addPhoto(PhotoDto photoDto) throws Exception {
        photoMapper.insertPhoto(photoDto);
    }

    // 시공 사례 ID로 사진 조회
    public List<PhotoDto> getPhotosByPId(long projectId) throws Exception {
        return photoMapper.getPhotosByPId(projectId);
    }
    
    // 리뷰 ID로 사진 조회
    public List<PhotoDto> getPhotosByRId(long reviewId) throws Exception {
        return photoMapper.getPhotosByRId(reviewId);
    }


    // 사진 삭제
    @Transactional
    public void removePhoto(Long photoId) throws Exception {
        photoMapper.deletePhoto(photoId);
    }
}
