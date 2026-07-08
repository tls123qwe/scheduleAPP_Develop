package com.example.scheduleapp_develop.dto.scheduleDto;

import lombok.Getter;

@Getter

public class CreateScheduleRequest {

    private String title;
    private String contents;
    private String password;

}