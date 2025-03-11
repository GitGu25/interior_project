//package org.big.domain;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.time.LocalDateTime;
//
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//@Table(name = "interior_estimate_requests")	// 실제 DB 테이블 이름과 매핑
//public class EstimateRequest {
//
//// 필드(컬럼) 정의
//	
//    @Id //기본 키 지정
//    @GeneratedValue(strategy = GenerationType.IDENTITY) //IDENTITY 방식: 자동 증가(AUTO_INCREMENT)
//    private Long interiorEstimateId;
//
//    @Column(nullable = false)	//필수 입력값(Not Null)
//    private String interiorEstimateName;
//
//    @Column(nullable = false)
//    private String interiorEstimatePassword;
//
//    @Lob //긴 텍스트(CLOB, BLOB) 데이터 저장 가능, CLOB : 길이가 긴 문자열 저장에 사용
//    @Column(nullable = false)
//    private String interiorEstimateRequestText;
//
//    @Column(nullable = false)
//    @Enumerated(EnumType.STRING) // ENUM(열거형)타입을 DB에 문자열로 저장, '견적 문의', '시공 진행', '시공 완료'같은 상태를 ENUM으로 관리
//    private EstimateStatus interiorEstimateStatus = EstimateStatus.견적_문의; // '견적 문의'로 기본값 설정
//
//    @Column(nullable = false, updatable = false)
//    private LocalDateTime interiorEstimateCreatedAt = LocalDateTime.now();
//    //LocalDateTime interiorEstimateCreatedAt → 작성일 필드
//    //updatable = false → 한 번 저장된 후 수정 불가능
//    //LocalDateTime.now() → 현재 시간을 기본값으로 설정
//    
//    public enum EstimateStatus {
//        견적_문의, 시공_진행, 시공_완료
//    }
//}