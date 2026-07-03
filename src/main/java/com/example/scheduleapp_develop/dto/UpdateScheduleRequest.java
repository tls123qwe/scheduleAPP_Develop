package com.example.scheduleapp_develop.dto;

import lombok.Getter;

@Getter

public class UpdateScheduleRequest {

    private String author;
    private String title;
    private String contents;
    private String password;

}
