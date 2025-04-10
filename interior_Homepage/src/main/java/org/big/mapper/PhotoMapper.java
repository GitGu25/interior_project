package org.big.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.big.dto.PhotoDto;

@Mapper
public interface PhotoMapper {

    // 사진 등록
    void insertPhoto(PhotoDto photoDto);

    // 특정 프로젝트에 속한 사진 조회
    List<PhotoDto> getPhotosByPId(@Param("iphotoProjectId") long iphotoProjectId);

    // 특정 리뷰에 속한 사진 조회
    List<PhotoDto> getPhotosByRId(@Param("iphotoReviewId") long iphotoReviewId);

    // 사진 삭제
    void deletePhoto(@Param("iphotoId") Long iphotoId);
}