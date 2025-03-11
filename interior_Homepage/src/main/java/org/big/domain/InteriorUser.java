//package org.big.domain;
//
//import jakarta.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//public class InteriorUser {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "interior_user_id", nullable = false)
//    private Long id;  // 사용자 고유 ID
//
//    @Column(name = "interior_user_username", nullable = false, length = 255)
//    private String username;  // 사용자 이름
//
//    @Column(name = "interior_user_email", nullable = false, unique = true, length = 255)
//    private String email;  // 사용자 이메일 (유일성)
//
//    @Column(name = "interior_user_password", nullable = false, length = 255)
//    private String password;  // 사용자 비밀번호
//
//    @Column(name = "interior_user_role", length = 50)
//    private String role;  // 사용자 역할 (admin, contractor)
//
//    @Column(name = "interior_user_phone", length = 20)
//    private String phone;  // 전화번호
//
//    @Column(name = "interior_user_address", length = 255)
//    private String address;  // 주소
//
//    @Column(name = "interior_user_created_at", nullable = false, updatable = false)
//    private LocalDateTime createdAt = LocalDateTime.now();  // 가입일
//
//    // 생성일 자동 설정
//    @PrePersist
//    public void setCreatedAt() {
//        this.createdAt = LocalDateTime.now();
//    }
//
//    // 생성자, getter, setter 등 추가 가능
//}
