package com.example.scheduleapp_develop.dto.authDto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter

public class SignupResponse {

    private final Long Id;
    private final String userName;
    private final String email;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public SignupResponse(Long id, String userName, String email, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        Id = id;
        this.userName = userName;
        this.email = email;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
