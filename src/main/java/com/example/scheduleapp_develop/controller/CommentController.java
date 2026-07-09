package com.example.scheduleapp_develop.controller;

import com.example.scheduleapp_develop.dto.commentDto.*;
import com.example.scheduleapp_develop.repository.CommentRepository;
import com.example.scheduleapp_develop.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class CommentController {

    private final CommentService commentService;
    private final CommentRepository commentRepository;

    @PostMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<CreateCommentResponse> createComment(@PathVariable Long scheduleId,
                                                               @RequestBody CreateCommentRequest request,
                                                               HttpServletRequest servletRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(scheduleId, request, servletRequest));
    }

    @GetMapping("/Schedules/{scheduleId}/comments")
    public ResponseEntity<List<GetCommentResponse>> getAllComments() {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getAllComments());
    }

    @GetMapping("/Schedules/{scheduleId}/comments/{commentId}")
    public ResponseEntity<GetCommentResponse> getOneComment(@PathVariable Long commentId) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getOneComment(commentId));
    }

    @PutMapping("/Schedules/{scheduleId}/comments/{commentId}")
    public ResponseEntity<UpdateCommentResponse> updateComment(@PathVariable Long commentId,
                                                               @RequestBody UpdateCommentRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.updateComment(commentId, request));
    }

    @DeleteMapping("/Schedules/{scheduleId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {

        commentService.deleteComment(commentId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
