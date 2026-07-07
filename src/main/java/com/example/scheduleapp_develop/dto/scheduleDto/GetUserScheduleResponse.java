package com.example.scheduleapp_develop.dto.scheduleDto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter

public class GetUserScheduleResponse {

    private final long Id;
    private final String author;
    private final String title;
    private final String contents;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;


    public GetUserScheduleResponse(long id, String author, String title, String contents, LocalDateTime createdAt, LocalDateTime modifiedAt) {

        Id = id;
        this.author = author;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;

    }
}
