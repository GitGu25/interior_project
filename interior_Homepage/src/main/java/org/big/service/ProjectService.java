package org.big.service;

import java.util.List;

import org.big.dto.ProjectDto;

public interface ProjectService {

	// 리뷰 목록 조회
		List<ProjectDto> getProjects(int page, int size) throws Exception;

		// 총 페이지 개수 계산
		// getTotalPages(int size)는 서비스에서만 계산하도록 유지
		int getTotalPages(int size) throws Exception;

		ProjectDto getProjectById(int iprojectId) throws Exception; // 특정 리뷰 조회

		void updateProject(ProjectDto projectDto) throws Exception; // 리뷰 수정

		void deleteProject(int iprojectId) throws Exception; // 리뷰 삭제

		// ✅ 리뷰 + 사진 업로드 묶어서 저장
		void projectBundle(ProjectDto projectDto) throws Exception;

		List<ProjectDto> getLatestProjects();

		void updatePBundle(ProjectDto projectDto, List<Long> deletePhotoIds) throws Exception;

}
