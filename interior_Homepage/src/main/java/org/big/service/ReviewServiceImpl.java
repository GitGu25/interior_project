package org.big.service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.big.dto.PhotoDto;
import org.big.dto.ReviewDto;
import org.big.mapper.PhotoMapper;
import org.big.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

	private static final String UPLOAD_DIR = "C:/Temp/photo/images/";
	
	 @Autowired
	    private ReviewMapper reviewMapper;

	 @Autowired
	    private PhotoMapper photoMapper;
	
	@Autowired
	public ReviewServiceImpl(ReviewMapper reviewMapper, PhotoMapper photoMapper) {
		this.reviewMapper = reviewMapper;
		this.photoMapper = photoMapper;
	}

	// 리뷰 목록 조회 (페이지네이션 적용)
	@Override
	public List<ReviewDto> getReviews(int page, int size) throws Exception {
		int offset = (page - 1) * size; // 페이지 오프셋 계산
		return reviewMapper.getReviews(offset, size);
	}

	// 전체 리뷰 개수 조회 (페이지네이션용)
	public int getTotalReviewCount() throws Exception {
		return reviewMapper.getTotalReviewCount();
	}

	// 전체 페이지 수 계산
	public int getTotalPages(int size) throws Exception {
		int totalReviews = getTotalReviewCount();
		return (int) Math.ceil((double) totalReviews / size);
	}

	// 리뷰 저장 + 사진 업로드 및 저장
	@Override
	@Transactional
	public void reviewBundle(ReviewDto reviewDto) throws Exception {
	    // 1. 리뷰 저장
	    reviewMapper.insertReview(reviewDto);
	    Long ireviewId = reviewDto.getIreviewId();
	    System.out.println("업로드된 리뷰ID: " + ireviewId);

	    // 2. 업로드된 파일 처리
	    List<MultipartFile> photos = reviewDto.getUploadFiles();
	    if (photos == null || photos.isEmpty()) {
	        System.out.println("첨부된 사진이 없습니다.");
	        return;
	    }

	    // 3. 저장 경로 생성
	    File dir = new File(UPLOAD_DIR);
	    if (!dir.exists()) dir.mkdirs();

	    // 4. 각 파일 처리
	    for (MultipartFile photo : photos) {
	        if (!photo.isEmpty()) {
	            PhotoDto photoDto = saveFileAndCreateDto(photo, ireviewId);
	            photoMapper.insertPhoto(photoDto);
	        }
	    }
	}

	// ✅ 유틸 메서드: 파일 저장 + PhotoDto 생성
	private PhotoDto saveFileAndCreateDto(MultipartFile photo, Long ireviewId) throws Exception {
	    String originalFilename = photo.getOriginalFilename();
	    String extension = originalFilename.substring(originalFilename.lastIndexOf('.') + 1).toLowerCase();

	    // 허용 확장자 검사
	    List<String> allowedExtensions = List.of("jpg", "jpeg", "png", "gif");
	    if (!allowedExtensions.contains(extension)) {
	        throw new IllegalArgumentException("허용되지 않은 파일 형식입니다: " + extension);
	    }

	    String newFilename = UUID.randomUUID().toString() + "_" + originalFilename;
	    File destFile = new File(UPLOAD_DIR + newFilename);
	    photo.transferTo(destFile); // 실제 파일 저장

	    // PhotoDto 생성 및 설정
	    PhotoDto photoDto = new PhotoDto();
	    photoDto.setIphotoReviewId(ireviewId);
	    photoDto.setIphotoProjectId(null);
	    photoDto.setIphotoFilename(newFilename);
	    photoDto.setIphotoExtension(extension);
	    photoDto.setIphotoUploadedAt(LocalDateTime.now());

	    return photoDto;
	}


	// 특정 리뷰 조회
	@Override
	public ReviewDto getReviewById(int ireviewId) throws Exception {
		return reviewMapper.getReviewById(ireviewId);
	}

	// 리뷰 수정
	@Override
	public void updateReview(ReviewDto reviewDto) throws Exception {
		reviewMapper.updateReview(reviewDto);
	}

	// 리뷰 삭제
	@Override
	public void deleteReview(int ireviewId) throws Exception {
		reviewMapper.deleteReview(ireviewId);
	}
}