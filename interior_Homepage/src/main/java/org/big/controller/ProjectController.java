package org.big.controller;

import java.util.List;

import org.big.dto.PhotoDto;
import org.big.dto.ProjectDto;
import org.big.service.PhotoService;
import org.big.service.ProjectService;
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
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	private final PhotoService photoService;

	// 시공 사례 목록
	@GetMapping("/project/list")
	public String listProjects(@RequestParam(defaultValue = "1") int page, // 기본값 1
			@RequestParam(defaultValue = "12") int size, // 페이지당 12개 유지
			Model model) {
		try {
			List<ProjectDto> projects = projectService.getProjects(page, size);
			
			// 총 페이지 수를 계산하는 서비스 메소드
			int totalPages = projectService.getTotalPages(size);

			// 모델에 데이터 추가
			model.addAttribute("projects", projects);
			model.addAttribute("currentPage", page);
			model.addAttribute("totalPages", totalPages);
			model.addAttribute("size", size);
			model.addAttribute("bannerTitle", "시공 사례");
			model.addAttribute("bannerDescription", "시공사례 및 작업현장");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "시공 사례 목록을 불러오는 중 오류가 발생했습니다.");
		}

		return "thymeleaf/project"; // 시공 사례 페이지
	}

	// 시공 사례 상세 보기
	@GetMapping("/project/view/{iprojectId}")
	public String getProjectDetail(@PathVariable int iprojectId, Model model) throws Exception {
		ProjectDto project = projectService.getProjectById(iprojectId); // 프로젝트 데이터 조회

		if (project == null) {
			return "error/404";
		}

		
		// 프로젝트에 연결된 사진 리스트
		List<PhotoDto> photos = photoService.getPhotosByPId(iprojectId);
		project.setPhotos(photos); // 프로젝트에 이미지 리스트 추가

		model.addAttribute("project", project);
		return "thymeleaf/projectDetail"; // 프로젝트 상세 페이지
	}

	// 시공 사례 작성 페이지 열기
	@GetMapping("/project/write")
	public String openProjectWrite() {
		return "thymeleaf/projectWrite"; // 작성 페이지로 이동
	}

	// 시공 사례 작성 처리
	@PostMapping("/project/write")
	public String insertProject(@ModelAttribute ProjectDto projectDto,
			@RequestParam("uploadFiles") List<MultipartFile> uploadFiles) throws Exception {
		projectDto.setUploadFiles(uploadFiles); // DTO에 이미지 리스트 세팅
		projectService.projectBundle(projectDto); // 서비스 호출
		return "redirect:/project/list"; // 시공 사례 목록 페이지로 리다이렉트
	}

	// 시공 사례 수정 페이지 열기
	@GetMapping("/project/update/{iprojectId}")
	public String openProjectUpdate(@PathVariable int iprojectId, Model model) throws Exception {
		ProjectDto project = projectService.getProjectById(iprojectId);

		if (project == null) {
			return "error/404";
		}

		List<PhotoDto> photos = photoService.getPhotosByPId(iprojectId);
		project.setPhotos(photos);

		List<PhotoDto> photoList = photoService.getPhotosByPId(iprojectId);
		
		model.addAttribute("project", project);
		model.addAttribute("photoList", photoList);
		return "thymeleaf/projectUpdate";
	}

	// 시공 사례 수정 처리
	@PostMapping("/project/update/{iprojectId}")
	public String updateProject(@PathVariable int iprojectId, 
								@ModelAttribute ProjectDto projectDto,
								@RequestParam(required = false) List<MultipartFile> uploadFiles,
								@RequestParam(required = false, name = "deletePhotoIds") List<Long> deletePhotoIds,
								Model model) throws Exception {

		projectDto.setUploadFiles(uploadFiles);
		projectDto.setIprojectId((long) iprojectId);

		// 삭제할 사진 ID를 서비스로 전달하여 삭제 처리
		projectService.updatePBundle(projectDto, deletePhotoIds);

		return "redirect:/project/list"; // 수정 후 목록 페이지로 리다이렉트
	}

	// 시공 사례 삭제 처리
	@PostMapping("/project/delete")
	public String deleteProject(@RequestParam("iprojectId") int iprojectId) throws Exception {
		projectService.deleteProject(iprojectId); // 프로젝트 삭제

		return "redirect:/project/list"; // 삭제 후 목록 페이지로 이동
	}
}
