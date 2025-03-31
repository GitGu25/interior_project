package org.big.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.big.dto.PhotoDto;
import org.big.mapper.PhotoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoMapper photoMapper;

    // application.properties에서 파일 경로를 읽어옴
    @Value("${file.upload-dir}")
    private String uploadDir;

    // 사진 파일 저장
    @Override
    public String savePhoto(MultipartFile file) throws IOException {
        // 파일 이름 생성 (현재 시간을 이용해 유니크한 파일 이름 생성)
        String fileName = System.currentTimeMillis() + "_" + UUID.randomUUID() + "_" + file.getOriginalFilename();

        // 파일 저장 경로 (업로드 디렉토리 경로와 파일 이름 합침)
        Path uploadPath = Paths.get(uploadDir + fileName);

        // 업로드 디렉토리가 없으면 생성
        Files.createDirectories(uploadPath.getParent()); // 부모 디렉토리 생성

        // 파일을 지정된 경로에 저장
        Files.copy(file.getInputStream(), uploadPath);

        // 저장된 파일 URL 반환 (웹에서 접근할 수 있는 URL)
        return "/uploads/" + fileName;
    }

    // 사진 DB에 저장
    @Override
    public void insertPhoto(PhotoDto photoDto) {
        photoMapper.insertPhoto(photoDto);
    }

    // 리뷰에 연결된 모든 사진을 조회
    @Override
    public List<PhotoDto> getPhotos(int reviewId) {
        return photoMapper.getPhotos(reviewId);
    }

    // 특정 리뷰에 연결된 모든 사진 삭제
    @Override
    public void deletePhotos(int reviewId) {
        // DB에서 해당 사진 삭제
        photoMapper.deletePhotos(reviewId);

        // 실제 파일 삭제
        // 파일 경로를 통해 파일을 삭제해야 할 경우 처리
        Path uploadPath = Paths.get(uploadDir + reviewId);  // 파일 경로가 DB에 저장된 값으로부터 결정될 수 있음
        try {
            Files.deleteIfExists(uploadPath);
        } catch (IOException e) {
            // 파일 삭제 실패 로그
            System.err.println("파일 삭제 중 오류 발생: " + e.getMessage());
        }
    }
}
