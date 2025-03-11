//package org.big.domain;
//
//import jakarta.persistence.*;
//
//@Entity
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String username;  // 로그인 ID
//    private String password;  // 암호화된 비밀번호
//
//    @OneToOne
//    @JoinColumn(name = "interior_user_id")  // InteriorUser와 1:1 관계
//    private InteriorUser interiorUser;  // InteriorUser와 연결
//
//    // 생성자, getter, setter 등 추가
//    
//}
