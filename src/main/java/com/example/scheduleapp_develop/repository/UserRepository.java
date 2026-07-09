package com.example.scheduleapp_develop.repository;

import com.example.scheduleapp_develop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // 이메일로 유저를 조회 할 때
    // 로그인 할 때 사용
    Optional<User> findByEmail(String email);

    // 이메일 중복 검사
    // 회원가입 할 때 사용
    boolean existsByEmail(String email);
}
