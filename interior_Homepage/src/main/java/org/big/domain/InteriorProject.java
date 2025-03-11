//package org.big.domain;
//
//import java.time.LocalDateTime;
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//@Table(name = "interior_projects")  // 실제 DB 테이블 이름과 매핑
//public class InteriorProject {
//
//    // 시공 프로젝트 고유 ID (PK)
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 방식
//    @Column(name = "interiorProjectId", nullable = false)
//    private Long interiorProjectId;
//
//    // 업자 ID (FK)
//    @ManyToOne(fetch = FetchType.LAZY)  // 외래 키 관계
//    @JoinColumn(name = "interiorProjectUserId", nullable = false)  // 외래 키 컬럼을 카멜 케이스로 설정
//    private InteriorUser interiorUser;  // 외래 키로 연결할 InteriorUser 엔티티 클래스 (업자 테이블)
//
//    // 시공 사례 이름
//    @Column(name = "interiorProjectName", nullable = false, length = 255)
//    private String interiorProjectName;
//
//    // 시공 사례 설명
//    @Lob  // 긴 텍스트 데이터 저장 가능
//    @Column(name = "interiorProjectDescription")
//    private String interiorProjectDescription;
//
//    // 시공 사례 업로드 일시
//    @Column(name = "interiorProjectCreatedAt", nullable = false, updatable = false)
//    private LocalDateTime interiorProjectCreatedAt = LocalDateTime.now();  // 현재 시간으로 기본값 설정
//
//    // 작성일 자동 설정
//    @PrePersist
//    public void setCreatedAt() {
//        this.interiorProjectCreatedAt = LocalDateTime.now();
//    }
//}
