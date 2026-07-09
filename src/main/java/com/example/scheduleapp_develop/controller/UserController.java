package com.example.scheduleapp_develop.controller;

import com.example.scheduleapp_develop.dto.scheduleDto.*;
import com.example.scheduleapp_develop.dto.userDto.*;
import com.example.scheduleapp_develop.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;

    // 유저 생성
    // 회원가입 기능 생성 후 불필요
    /* @PostMapping("/users")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(request));
    } */

    // 유저 단건 조회
    @GetMapping("/users/{userId}")
    public ResponseEntity<GetUserResponse> getOneUser(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body((userService.getOneUser(userId)));
    }

    // 유저 전체 조회
    @GetMapping("/users")
    public ResponseEntity<List<GetUserResponse>> getAllUser() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    // 유저 수정
    @PutMapping("/users/{userId}")
    public ResponseEntity<UpdateUserResponse> updateSchedule(@PathVariable Long userId,
                                                             @RequestBody UpdateUserRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(userId, request));
    }

    // 유저 삭제
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long userId,
                                               @RequestBody DeleteUserRequest request) {
        userService.deleteUser(userId, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
