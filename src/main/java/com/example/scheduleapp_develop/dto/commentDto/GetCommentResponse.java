package com.example.scheduleapp_develop.dto.commentDto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter

public class GetCommentResponse {

    private final Long commentId;
    private final String userName;
    private final String contents;
    private final LocalDateTime CreatedAt;
    private final LocalDateTime modifiedAt;


    public GetCommentResponse(Long commentId, String userName, String contents, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.commentId = commentId;
        this.userName = userName;
        this.contents = contents;
        CreatedAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
