package com.example.scheduleapp_develop.dto.commentDto;

import com.example.scheduleapp_develop.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter

public class CreateCommentResponse {

    private final Long commentId;
    private final Long scheduleId;
    private final String userName;
    private final String contents;
    private final LocalDateTime CreatedAt;
    private final LocalDateTime modifiedAt;

    // DTO에서 응답 데이터 변환해보기
    public CreateCommentResponse(Comment comment) {
        this.commentId = comment.getCommentId();
        this.scheduleId = comment.getSchedule().getId();
        this.userName = comment.getUser().getUserName();
        this.contents = comment.getContents();
        this.CreatedAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}
