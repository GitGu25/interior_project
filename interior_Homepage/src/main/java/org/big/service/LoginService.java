//package org.big.service;
//
//import org.big.domain.User;
//import org.big.repository.UserRepository;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class LoginService {
//
//    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//    private final UserRepository userRepository;
//
//    // 생성자 주입
//    public LoginService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    // 로그인 검증 메서드
//    public boolean authenticate(String enteredPassword, String username) {
//        // 사용자 정보 가져오기
//        User user = userRepository.findByUsername(username);  // 예: 사용자 이름으로 DB 조회
//        
//        if (user == null) {
//            return false;  // 사용자 없으면 false
//        }
//
//        // 입력된 비밀번호와 DB에 저장된 비밀번호를 비교
//        return passwordEncoder.matches(enteredPassword, user.getPassword());
//    }
//}
