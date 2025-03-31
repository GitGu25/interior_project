package org.big.controller;

import java.util.List;

import org.big.dto.PhotoDto;
import org.big.dto.ReviewDto;
import org.big.service.PhotoService;
import org.big.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    
    @Autowired
    private PhotoService photoService;

    // 📌 리뷰 목록 (페이지네이션 가능)
    @GetMapping("/review/list")
    public String listReviews(Model model,
                              @RequestParam(defaultValue = "1") int page,   // 페이지 번호 (기본값: 1)
                              @RequestParam(defaultValue = "10") int size) { // 페이지당 항목 수 (기본값: 10)
        try {
            // 리뷰 목록을 페이지네이션으로 가져오는 서비스 메소드
            List<ReviewDto> reviews = reviewService.getReviews(page, size);
            
            // 총 페이지 수를 계산하는 서비스 메소드
            int totalPages = reviewService.getTotalPages(size);

            // 모델에 데이터를 담아서 뷰로 전달
            model.addAttribute("reviews", reviews);   // 리뷰 목록
            model.addAttribute("currentPage", page);   // 현재 페이지 번호
            model.addAttribute("totalPages", totalPages); // 총 페이지 수
            model.addAttribute("size", size);          // 페이지당 항목 수
            model.addAttribute("bannerTitle", "고객 후기");
            model.addAttribute("bannerDescription", "시공 후기 게시판");
        } catch (Exception e) {
            e.printStackTrace();  // 예외 처리
            model.addAttribute("errorMessage", "리뷰 목록을 불러오는 중 오류가 발생했습니다.");
        }

        return "thymeleaf/review"; // 반환할 뷰 이름
    }

    // 📌 리뷰 작성 페이지
    @GetMapping("/review/write")
    public String openReviewWrite() {
        return "thymeleaf/reviewWrite"; // 리뷰 작성 페이지로 이동
    }

    // 📌 리뷰 작성 처리
    @PostMapping("/review/write")
    public String submitReview(@ModelAttribute ReviewDto reviewDto,
                               MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
    	List<MultipartFile> fileList = multipartHttpServletRequest.getFiles("reviewImage");
        reviewService.insertReview(reviewDto, multipartHttpServletRequest); // 리뷰 데이터 저장

		/*
		 * // 이미지를 업로드한 경우 처리 if (reviewImage != null && !reviewImage.isEmpty()) {
		 * String imageUrl = photoService.savePhoto(reviewImage); // 이미지 저장 PhotoDto
		 * photoDto = new PhotoDto();
		 * photoDto.setIphotoReviewId(reviewDto.getIreviewId()); // 저장된 리뷰 ID와 연결
		 * photoDto.setIphotoUrl(imageUrl); // 이미지 URL 저장
		 * photoDto.setIphotoDescription("리뷰 이미지"); // 이미지 설명 (기본값)
		 * 
		 * photoService.insertPhoto(photoDto); // 이미지 DB 저장 }
		 */

        return "redirect:/review/list"; // 리뷰 목록 페이지로 이동
    }

    
	/*
	 * // 📌 특정 리뷰 조회
	 * 
	 * @GetMapping("/review/view/{reviewId}") public String
	 * getReviewDetail(@PathVariable int reviewId, Model model) throws Exception {
	 * ReviewDto review = reviewService.getReviewById(reviewId); // 리뷰 데이터 조회
	 * List<PhotoDto> photos = photoService.getPhotos(reviewId); // 해당 리뷰의 이미지 목록 조회
	 * 
	 * if (review == null) { return "error/404"; // 리뷰가 없으면 404 페이지로 이동 }
	 * 
	 * model.addAttribute("review", review); model.addAttribute("photos", photos);
	 * // 이미지 리스트 추가 return "thymeleaf/reviewDetail"; // 리뷰 상세 페이지 반환 }
	 */

	/*
	 * // 📌 리뷰 수정 페이지
	 * 
	 * @GetMapping("/review/update/{reviewId}") public String
	 * openReviewUpdate(@PathVariable int reviewId, Model model) throws Exception {
	 * ReviewDto review = reviewService.getReviewById(reviewId); List<PhotoDto>
	 * photos = photoService.getPhotos(reviewId);
	 * 
	 * if (review == null) { return "error/404"; }
	 * 
	 * model.addAttribute("review", review); model.addAttribute("photos", photos);
	 * // 이미지 리스트 추가 return "thymeleaf/reviewUpdate"; }
	 * 
	 * // 리뷰 수정 처리
	 * 
	 * @PostMapping("/review/update") public String updateReview(@ModelAttribute
	 * ReviewDto reviewDto,
	 * 
	 * @RequestParam(value = "reviewImage", required = false) MultipartFile
	 * reviewImage) throws Exception { reviewService.updateReview(reviewDto); // 리뷰
	 * 데이터 수정
	 * 
	 * // 새로운 이미지가 업로드된 경우 if (reviewImage != null && !reviewImage.isEmpty()) {
	 * String imageUrl = photoService.savePhoto(reviewImage); // 새로운 이미지 저장 PhotoDto
	 * photoDto = new PhotoDto();
	 * photoDto.setIphotoReviewId(reviewDto.getIreviewId()); // 리뷰 ID와 연결
	 * photoDto.setIphotoUrl(imageUrl); // 이미지 URL 저장
	 * photoDto.setIphotoDescription("리뷰 이미지");
	 * 
	 * photoService.insertPhoto(photoDto); // 새 이미지 DB 저장 }
	 * 
	 * return "redirect:/review/list"; // 수정 후 목록 페이지로 이동 }
	 * 
	 * // 📌 리뷰 삭제 처리
	 * 
	 * @PostMapping("/review/delete") public String
	 * deleteReview(@RequestParam("reviewId") int reviewId) throws Exception {
	 * photoService.deletePhotos(reviewId); // 해당 리뷰의 이미지 삭제
	 * reviewService.deleteReview(reviewId); // 리뷰 삭제
	 * 
	 * return "redirect:/review/list"; }
	 */

}
