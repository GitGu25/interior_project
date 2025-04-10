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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	private final PhotoService photoService;

	// 📌 리뷰 목록 (페이지네이션 가능)
	@GetMapping("/review/list")
	public String listReviews(Model model, @RequestParam(defaultValue = "1") int page, // 페이지 번호 (기본값: 1)
			@RequestParam(defaultValue = "10") int size) { // 페이지당 항목 수 (기본값: 10)
		try {
			// 리뷰 목록을 페이지네이션으로 가져오는 서비스 메소드
			List<ReviewDto> reviews = reviewService.getReviews(page, size);

			// 총 페이지 수를 계산하는 서비스 메소드
			int totalPages = reviewService.getTotalPages(size);

			// 모델에 데이터를 담아서 뷰로 전달
			model.addAttribute("reviews", reviews); // 리뷰 목록
			model.addAttribute("currentPage", page); // 현재 페이지 번호
			model.addAttribute("totalPages", totalPages); // 총 페이지 수
			model.addAttribute("size", size); // 페이지당 항목 수
			model.addAttribute("bannerTitle", "고객 후기");
			model.addAttribute("bannerDescription", "시공 후기 게시판");
		} catch (Exception e) {
			e.printStackTrace(); // 예외 처리
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
	public String insertReview(@ModelAttribute ReviewDto reviewDto) throws Exception {
	    reviewService.reviewBundle(reviewDto); // reviewDto에 파일도 포함되어 있음
	    return "redirect:/review/list";
	}

	// 📌 특정 리뷰 조회
	@GetMapping("/review/view/{ireviewId}")
	public String getReviewDetail(@PathVariable int ireviewId, Model model) throws Exception {
	    ReviewDto review = reviewService.getReviewById(ireviewId); // 리뷰 데이터 조회

	    if (review == null) {
	        return "error/404";
	    }

	    // 📌 리뷰에 연결된 사진 리스트를 설정 (✔️ 여기 메서드 이름 수정됨)
	    List<PhotoDto> photos = photoService.getPhotosByRId(ireviewId);
	    review.setPhotos(photos); // 리뷰 객체에 이미지 리스트 주입

	    model.addAttribute("review", review); // 리뷰 + 이미지 포함 상태로 전달
	    return "thymeleaf/reviewDetail"; // 리뷰 상세 페이지 반환
	}

	// 📌 리뷰 수정 페이지
	@GetMapping("/review/update/{ireviewId}")
	public String openReviewUpdate(@PathVariable int ireviewId, Model model) throws Exception {
		ReviewDto review = reviewService.getReviewById(ireviewId);

		if (review == null) {
			return "error/404";
		}

		model.addAttribute("review", review);
		return "thymeleaf/reviewUpdate"; // 리뷰 수정 페이지로 이동
	}

	// 리뷰 수정 처리
	@PostMapping("/review/update")
	public String updateReview(@ModelAttribute ReviewDto reviewDto) throws Exception {
		reviewService.updateReview(reviewDto); // 리뷰 데이터 수정

		return "redirect:/review/list"; // 수정 후 목록 페이지로 이동
	}

	// 📌 리뷰 삭제 처리
	@PostMapping("/review/delete")
	public String deleteReview(@RequestParam("ireviewId") int ireviewId) throws Exception {
		reviewService.deleteReview(ireviewId); // 리뷰 삭제

		return "redirect:/review/list"; // 삭제 후 목록 페이지로 이동
	}
}