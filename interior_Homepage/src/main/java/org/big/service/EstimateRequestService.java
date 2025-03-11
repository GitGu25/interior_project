//package org.big.service;
//
//import org.big.domain.EstimateRequest;
//import org.big.repository.EstimateRequestRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class EstimateRequestService {
//
//    @Autowired
//    private EstimateRequestRepository estimateRequestRepository;
//
//    // 모든 견적 요청 가져오기
//    public List<EstimateRequest> getAllRequests() {
//        return estimateRequestRepository.findAll();
//    }
//
//    // ID로 견적 요청 가져오기
//    public Optional<EstimateRequest> getRequestById(Long id) {
//        return estimateRequestRepository.findById(id);
//    }
//
//    // 견적 요청 저장하기
//    public EstimateRequest saveRequest(EstimateRequest estimateRequest) {
//        return estimateRequestRepository.save(estimateRequest);
//    }
//
//    // 견적 요청 삭제하기
//    public void deleteRequest(Long id) {
//        estimateRequestRepository.deleteById(id);
//    }
//}
