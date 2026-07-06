package com.example.scheduleapp_develop.dto.scheduleDto;

import lombok.Getter;

@Getter

public class UpdateScheduleRequest {

    private Long userId;
    private String title;
    private String contents;
    private String password;

}
