package org.big.service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.big.dto.PhotoDto;
import org.big.dto.ProjectDto;
import org.big.mapper.PhotoMapper;
import org.big.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional 
public class ProjectServiceImpl implements ProjectService {

	private static final String UPLOAD_DIR = "C:/Temp/photo/images/";
	
	 @Autowired
	    private ProjectMapper projectMapper;

	 @Autowired
	    private PhotoMapper photoMapper;
	
	@Autowired
	public ProjectServiceImpl(ProjectMapper projectMapper, PhotoMapper photoMapper) {
		this.projectMapper = projectMapper;
		this.photoMapper = photoMapper;
	}

	// 리뷰 목록 조회 (페이지네이션 적용)
	@Override
	public List<ProjectDto> getProjects(int page, int size) throws Exception {
	    int offset = (page - 1) * size; // 페이지 오프셋 계산
	    List<ProjectDto> projects = projectMapper.getProjects(offset, size);

	    // 각 리뷰에 연결된 첫 번째 사진의 경로를 thumbUrl에 설정
	    for (ProjectDto project : projects) {
	    	List<PhotoDto> photos = photoMapper.getPhotosByPId(project.getIprojectId());
	        if (!photos.isEmpty()) {
	            String thumbUrl = "/uploads/" + photos.get(0).getIphotoFilename(); // 첫 번째 사진의 경로
	            project.setThumbUrl(thumbUrl); // 리뷰에 thumbUrl 설정
	        }
	    }
	    return projects;
	}


	// 전체 리뷰 개수 조회 (페이지네이션용)
	public int getTotalProjectCount() throws Exception {
		return projectMapper.getTotalProjectCount();
	}

	// 전체 페이지 수 계산
	public int getTotalPages(int size) throws Exception {
		int totalProjects = getTotalProjectCount();
		return (int) Math.ceil((double) totalProjects / size);
	}

	// 리뷰 저장 + 사진 업로드 및 저장
	@Override
	@Transactional 
	public void projectBundle(ProjectDto projectDto) throws Exception {
	    // 1. 리뷰 저장
		projectMapper.insertProject(projectDto);
	    Long iprojectId = projectDto.getIprojectId();
	    System.out.println("업로드된 리뷰ID: " + iprojectId);

	    // 2. 업로드된 파일 처리
	    List<MultipartFile> photos = projectDto.getUploadFiles();
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
	            PhotoDto photoDto = saveFileAndCreateDto(photo, iprojectId);
	            photoMapper.insertPhoto(photoDto);
	        }
	    }
	    System.out.println("파일처리함");
	}

	// ✅ 유틸 메서드: 파일 저장 + PhotoDto 생성
	private PhotoDto saveFileAndCreateDto(MultipartFile photo, Long iprojectId) throws Exception {
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
	    photoDto.setIphotoReviewId(null);
	    photoDto.setIphotoProjectId(iprojectId);
	    photoDto.setIphotoFilename(newFilename);
	    photoDto.setIphotoExtension(extension);
	    photoDto.setIphotoUploadedAt(LocalDateTime.now());
	    System.out.println("전달할값" + photoDto);
	    
	    return photoDto;
	}


	// 특정 프로젝트 조회
	@Override
	public ProjectDto getProjectById(int iprojectId) throws Exception {
		return projectMapper.getProjectById(iprojectId);
	}

	// 프로젝트 수정
	@Override
	public void updateProject(ProjectDto projectDto) throws Exception {
		projectMapper.updateProject(projectDto);
	}

	// 새로 추가된 텍스트 + 이미지 함께 수정하는 메서드
	@Override
	@Transactional
	public void updatePBundle(ProjectDto projectDto, List<Long> deletePhotoIds) throws Exception {
		// 1. 프로젝트 본문 수정
		projectMapper.updateProject(projectDto);
	    Long iprojectId = projectDto.getIprojectId(); // 수정할 리뷰 ID

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
	    List<MultipartFile> photos = projectDto.getUploadFiles();
	    if (photos != null && !photos.isEmpty()) {
	        for (MultipartFile photo : photos) {
	            if (!photo.isEmpty()) {
	                PhotoDto photoDto = saveFileAndCreateDto(photo, iprojectId); // 파일 저장 및 DTO 생성
	                photoMapper.insertPhoto(photoDto); // DB에 새로운 사진 저장
	            }
	        }
	    }
	}

	
	// 리뷰 삭제
	@Override
	public void deleteProject(int iprojectId) throws Exception {
		projectMapper.deleteProject(iprojectId);
	}
	
	//메인에 리뷰가져오기
	@Override
	public List<ProjectDto> getLatestProjects() {
	    return projectMapper.selectLatestProjects();
	}

}