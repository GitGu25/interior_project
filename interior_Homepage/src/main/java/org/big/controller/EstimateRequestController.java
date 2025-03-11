//package org.big.controller;
//
//import org.big.domain.EstimateRequest;
//import org.big.service.EstimateRequestService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/estimate-requests")
//public class EstimateRequestController {
//
//    @Autowired
//    private EstimateRequestService estimateRequestService;
//
//    // 견적 요청 목록 가져오기
//    @GetMapping
//    public List<EstimateRequest> getAllRequests() {
//        return estimateRequestService.getAllRequests();
//    }
//
//    // 견적 요청 ID로 가져오기
//    @GetMapping("/{id}")
//    public Optional<EstimateRequest> getRequestById(@PathVariable Long id) {
//        return estimateRequestService.getRequestById(id);
//    }
//
//    // 견적 요청 저장하기
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public EstimateRequest createRequest(@RequestBody EstimateRequest estimateRequest) {
//        return estimateRequestService.saveRequest(estimateRequest);
//    }
//
//    // 견적 요청 삭제하기
//    @DeleteMapping("/{id}")
//    public void deleteRequest(@PathVariable Long id) {
//        estimateRequestService.deleteRequest(id);
//    }
//}
