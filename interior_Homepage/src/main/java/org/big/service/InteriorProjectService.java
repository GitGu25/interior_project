//package org.big.service;
//
//import org.big.domain.InteriorProject;
//import org.big.repository.InteriorProjectRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class InteriorProjectService {
//
//    @Autowired
//    private InteriorProjectRepository interiorProjectRepository;
//
//    // 모든 시공 프로젝트 가져오기
//    public List<InteriorProject> getAllProjects() {
//        return interiorProjectRepository.findAll();
//    }
//
//    // ID로 시공 프로젝트 가져오기
//    public Optional<InteriorProject> getProjectById(Long id) {
//        return interiorProjectRepository.findById(id);
//    }
//
//    // 시공 프로젝트 저장하기
//    public InteriorProject saveProject(InteriorProject interiorProject) {
//        return interiorProjectRepository.save(interiorProject);
//    }
//
//    // 시공 프로젝트 삭제하기
//    public void deleteProject(Long id) {
//        interiorProjectRepository.deleteById(id);
//    }
//}
