package org.big.controller;

import lombok.RequiredArgsConstructor;
import org.big.dto.PhotoDto;
import org.big.service.PhotoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/photo")
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;

    // 사진 추가
    @PostMapping("/add")
    public String addPhoto(@RequestBody PhotoDto photoDto) {
        try {
            photoService.addPhoto(photoDto);
            return "사진이 추가되었습니다.";
        } catch (Exception e) {
            return "사진 추가 중 오류 발생: " + e.getMessage();
        }
    }

    // 리뷰 ID로 사진 조회
    @GetMapping("/review/{reviewId}")
    public List<PhotoDto> getPhotosByRId(@PathVariable long reviewId) {
        try {
            return photoService.getPhotosByRId(reviewId);
        } catch (Exception e) {
            return null;
        }
    }

    // 시공 사례 ID로 사진 조회
    @GetMapping("/project/{projectId}")
    public List<PhotoDto> getPhotosByPId(@PathVariable long projectId) {
        try {
            return photoService.getPhotosByPId(projectId);
        } catch (Exception e) {
            return null;
        }
    }

    // 사진 삭제
    @DeleteMapping("/delete/{photoId}")
    public String removePhoto(@PathVariable long photoId) {
        try {
            photoService.removePhoto(photoId);
            return "사진이 삭제되었습니다.";
        } catch (Exception e) {
            return "사진 삭제 중 오류 발생: " + e.getMessage();
        }
    }
}
