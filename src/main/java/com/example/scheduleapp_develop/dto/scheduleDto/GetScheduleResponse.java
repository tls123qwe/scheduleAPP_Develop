package com.example.scheduleapp_develop.dto.scheduleDto;

import com.example.scheduleapp_develop.dto.commentDto.GetCommentResponse;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter

public class GetScheduleResponse {

    private final long Id;
    private final String author;
    private final String title;
    private final String contents;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final List<GetCommentResponse> comments;


    public GetScheduleResponse(long id, String author, String title, String contents, LocalDateTime createdAt, LocalDateTime modifiedAt, List<GetCommentResponse> comments) {

        Id = id;
        this.author = author;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.comments = comments;
    }
}
