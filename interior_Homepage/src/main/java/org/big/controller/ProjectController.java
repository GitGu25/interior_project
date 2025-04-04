package org.big.controller;

import java.util.List;
import org.big.dto.ProjectDto;
import org.big.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/projects/list")
    public String getProjects(
            @RequestParam(defaultValue = "1") int page,  // 기본값 1
            @RequestParam(defaultValue = "12") int size, // 페이지당 12개 유지
            Model model) {
        try {
            int totalProjects = projectService.getTotalProjectCount(); // 총 프로젝트 개수
            int totalPages = (int) Math.ceil((double) totalProjects / size);
            int offset = (page - 1) * size; // SQL OFFSET 계산

            List<ProjectDto> projects = projectService.getProjects(offset, size);

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
}
