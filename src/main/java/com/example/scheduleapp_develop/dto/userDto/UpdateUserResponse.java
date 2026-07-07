package com.example.scheduleapp_develop.dto.userDto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter

public class UpdateUserResponse {

    private final Long userId;
    private final String userName;
    private final String email;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public UpdateUserResponse(Long userId, String userName, String email, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
