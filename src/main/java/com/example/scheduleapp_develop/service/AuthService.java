package com.example.scheduleapp_develop.service;

import com.example.scheduleapp_develop.dto.authDto.LoginRequest;
import com.example.scheduleapp_develop.dto.authDto.SignupRequest;
import com.example.scheduleapp_develop.dto.authDto.SignupResponse;
import com.example.scheduleapp_develop.entity.User;
import com.example.scheduleapp_develop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.internal.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class AuthService {

    // 로그인 관련 정보는 유저 정보이기 때문에 유저 저장소 호출
    private final UserRepository userRepository;

    // 회원가입
    @Transactional
    public SignupResponse signup(SignupRequest request) {

        // 이메일 중복 확인
        // 회원가입 요청의 이메일이 userRepository에 이미 있는지 확인
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일 입니다.");
        }

        // 중복이 아니면
        // 유저 객체 생성
        User user = new User(
                request.getUserName(),
                request.getEmail(),
                request.getPassword());

        // DB에 저장
        User savedUser = userRepository.save(user);

        // 저장된 정보를 응답으로 반환
        return new SignupResponse(
                savedUser.getUserId(),
                savedUser.getUserName(),
                savedUser.getEmail(),
                savedUser.getCreatedAt(),
                savedUser.getModifiedAt());
    }

    // 로그인
    @Transactional
    public Long login(LoginRequest request) {

        // 유저 조회(이메일로)
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않은 유저입니다."));

        // 비밀번호 일치 확인
        if(!user.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치 하지 않습니다.");
        }

        // 로그인 성공 시 세션에 전달할 유저ID 반환
        return user.getUserId();
    }
}
