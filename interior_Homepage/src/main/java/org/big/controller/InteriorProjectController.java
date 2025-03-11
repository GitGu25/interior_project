//package org.big.controller;
//
//import org.big.domain.InteriorProject;
//import org.big.service.InteriorProjectService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/interior-projects")
//public class InteriorProjectController {
//
//    @Autowired
//    private InteriorProjectService interiorProjectService;
//
//    // 시공 프로젝트 목록 가져오기
//    @GetMapping
//    public List<InteriorProject> getAllProjects() {
//        return interiorProjectService.getAllProjects();
//    }
//
//    // 시공 프로젝트 ID로 가져오기
//    @GetMapping("/{id}")
//    public Optional<InteriorProject> getProjectById(@PathVariable Long id) {
//        return interiorProjectService.getProjectById(id);
//    }
//
//    // 시공 프로젝트 저장하기
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public InteriorProject createProject(@RequestBody InteriorProject interiorProject) {
//        return interiorProjectService.saveProject(interiorProject);
//    }
//
//    // 시공 프로젝트 삭제하기
//    @DeleteMapping("/{id}")
//    public void deleteProject(@PathVariable Long id) {
//        interiorProjectService.deleteProject(id);
//    }
//}
