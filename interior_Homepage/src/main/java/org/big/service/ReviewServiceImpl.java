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
	    List<ReviewDto> reviews = reviewMapper.getReviews(offset, size);

	    // 각 리뷰에 연결된 첫 번째 사진의 경로를 thumbUrl에 설정
	    for (ReviewDto review : reviews) {
	        List<PhotoDto> photos = photoMapper.getPhotosByRId(review.getIreviewId());
	        if (!photos.isEmpty()) {
	            String thumbUrl = "/uploads/" + photos.get(0).getIphotoFilename(); // 첫 번째 사진의 경로
	            review.setThumbUrl(thumbUrl); // 리뷰에 thumbUrl 설정
	        }
	    }
	    return reviews;
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
	    System.out.println("파일저장경로생성: " + dir);

	    // 4. 각 파일 처리
	    for (MultipartFile photo : photos) {
	        if (!photo.isEmpty()) {
	            PhotoDto photoDto = saveFileAndCreateDto(photo, ireviewId);
	            photoMapper.insertPhoto(photoDto);
	        }
	    }
	    System.out.println("파일처리함");
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
	    System.out.println("확장자 검사");

	    String newFilename = UUID.randomUUID().toString() + "_" + originalFilename;
	    File destFile = new File(UPLOAD_DIR + newFilename);
	    photo.transferTo(destFile); // 실제 파일 저장
	    System.out.println("파일저장함");

	    // PhotoDto 생성 및 설정
	    PhotoDto photoDto = new PhotoDto();
	    photoDto.setIphotoReviewId(ireviewId);
	    photoDto.setIphotoProjectId(null);
	    photoDto.setIphotoFilename(newFilename);
	    photoDto.setIphotoExtension(extension);
	    photoDto.setIphotoUploadedAt(LocalDateTime.now());
	    System.out.println("전달할값" + photoDto);
	    
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

	// 새로 추가된 텍스트 + 이미지 함께 수정하는 메서드
	@Override
	@Transactional
	public void updateRBundle(ReviewDto reviewDto, List<Long> deletePhotoIds) throws Exception {
	    // 1. 리뷰 본문 수정
	    reviewMapper.updateReview(reviewDto);
	    Long ireviewId = reviewDto.getIreviewId(); // 수정할 리뷰 ID

	    // 2. 삭제할 이미지가 있다면 삭제
	    if (deletePhotoIds != null && !deletePhotoIds.isEmpty()) {
	    	for (Long photoId : deletePhotoIds) {
	    	    PhotoDto photoDto = photoMapper.getPhotoById(photoId); // 🔄 올바른 메서드로 변경
	    	    if (photoDto != null) {
	    	        File file = new File(UPLOAD_DIR + photoDto.getIphotoFilename());
	    	        if (file.exists()) {
	    	            file.delete();
	    	        }
	    	        photoMapper.deletePhoto(photoId);
	    	    }
	    	}
	    }

	    // 3. 새 이미지 업로드 처리
	    List<MultipartFile> photos = reviewDto.getUploadFiles();
	    if (photos != null && !photos.isEmpty()) {
	        for (MultipartFile photo : photos) {
	            if (!photo.isEmpty()) {
	                PhotoDto photoDto = saveFileAndCreateDto(photo, ireviewId); // 파일 저장 및 DTO 생성
	                photoMapper.insertPhoto(photoDto); // DB에 새로운 사진 저장
	            }
	        }
	    }
	}

	
	// 리뷰 삭제
	@Override
	public void deleteReview(int ireviewId) throws Exception {
		reviewMapper.deleteReview(ireviewId);
	}
	
	//메인에 리뷰가져오기
	@Override
	public List<ReviewDto> getLatestReviews() {
	    return reviewMapper.selectLatestReviews();
	}

}