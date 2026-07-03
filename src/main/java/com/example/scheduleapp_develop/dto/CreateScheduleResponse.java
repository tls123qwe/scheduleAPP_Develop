package com.example.scheduleapp_develop.dto;

import lombok.Getter;

@Getter

public class CreateScheduleResponse {

    private final Long Id;
    private final String author;
    private final String title;
    private final String contents;


    public CreateScheduleResponse(Long id, String author, String title, String contents) {
        this.Id = id;
        this.author = author;
        this.title = title;
        this.contents = contents;
    }
}
