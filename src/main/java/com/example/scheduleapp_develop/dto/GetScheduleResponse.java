package com.example.scheduleapp_develop.dto;

import lombok.Getter;

@Getter

public class GetScheduleResponse {

    private final long Id;
    private final String author;
    private final String title;
    private final String contents;


    public GetScheduleResponse(long id, String author, String title, String contents) {

        Id = id;
        this.author = author;
        this.title = title;
        this.contents = contents;

    }
}
