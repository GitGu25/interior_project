//package org.big.domain;
//
//import java.time.LocalDateTime;
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import jakarta.persistence.PrePersist; // 비밀번호 암호화(엔티티 저장 전에 실행됨)
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Size;
//
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//@Table(name = "interior_guest_reviews")  // 실제 DB 테이블 이름과 매핑
//public class GuestReview {
//
//    // 필드(컬럼) 정의
//
//    @Id // 기본 키
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY 방식: 자동 증가(AUTO_INCREMENT)
//    private Long interiorReviewId;
//
//    @NotNull(message = "Review name cannot be null")
//    @Size(min = 3, max = 100, message = "Review name should be between 3 and 100 characters")
//    @Column(nullable = false)
//    private String interiorReviewName; // 비회원 이름 또는 닉네임
//
//    @NotNull(message = "Password cannot be null")
//    @Size(min = 6, max = 100, message = "Password should be between 6 and 100 characters")
//    @Column(nullable = false)
//    private String interiorReviewPassword; // 비회원 비밀번호
//
//    @Lob // 긴 텍스트 데이터 저장 가능
//    @Column(nullable = false)
//    private String interiorReviewText; // 리뷰 내용
//
//    @Column(nullable = false, updatable = false)
//    private LocalDateTime interiorReviewCreatedAt = LocalDateTime.now(); // 작성일시
//
//    // 비밀번호 암호화 (저장 전 실행)
//    @PrePersist
//    public void encryptPassword() {
//        if (interiorReviewPassword != null) {
//            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//            this.interiorReviewPassword = encoder.encode(interiorReviewPassword); // 비밀번호 암호화
//        }
//    }
//
//    // 작성일 자동 설정
//    @PrePersist
//    public void setCreatedAt() {
//        this.interiorReviewCreatedAt = LocalDateTime.now(); // 작성일 설정
//    }
//}
