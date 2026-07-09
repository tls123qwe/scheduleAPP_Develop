package com.example.scheduleapp_develop.controller;

import com.example.scheduleapp_develop.dto.authDto.LoginRequest;
import com.example.scheduleapp_develop.dto.authDto.SignupRequest;
import com.example.scheduleapp_develop.dto.authDto.SignupResponse;
import com.example.scheduleapp_develop.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")

public class AuthController {

    private final AuthService authService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(@Valid @RequestBody SignupRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signup(request));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<Void> login(@Valid @RequestBody LoginRequest request, HttpServletRequest servletRequest){

        // 이메일과 비밀번호 검증 후 유저ID만 반환
        Long userId = authService.login(request);

        // 세션 생성 또는 가져오기
        HttpSession session = servletRequest.getSession();

        // 세션에 로그인 유저ID 저장
        session.setAttribute("LOGIN_USER", userId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest servletRequest){

        // 기존에 세션이 있으면 가져오고, 없으면 NULL
        HttpSession session = servletRequest.getSession(false);

        // 세션이 존재한다면
        // 세션 무효화
        if(session != null) {
            session.invalidate();
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}