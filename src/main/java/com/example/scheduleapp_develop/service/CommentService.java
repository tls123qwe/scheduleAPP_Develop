package com.example.scheduleapp_develop.service;

import com.example.scheduleapp_develop.dto.commentDto.*;
import com.example.scheduleapp_develop.entity.Comment;
import com.example.scheduleapp_develop.entity.Schedule;
import com.example.scheduleapp_develop.entity.User;
import com.example.scheduleapp_develop.repository.CommentRepository;
import com.example.scheduleapp_develop.repository.ScheduleRepository;
import com.example.scheduleapp_develop.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    public CreateCommentResponse createComment(Long scheduleId, CreateCommentRequest request, HttpServletRequest servletRequest) {

        // 기존 세션 호출 없으면 null
        HttpSession session = servletRequest.getSession(false);

        // 세션이 없다 = 로그인 하지 않음
        // 세션값이 null이거나 로그인 정보가 null이면 예외 처리
        if (session == null || session.getAttribute("LOGIN_USER") == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }

        // 로그인 시 저장해둔 유저ID 가져오기
        Long userId = (Long) session.getAttribute("LOGIN_USER");

        // 가져온 유저ID로 DB에서 조회
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("없는 유저입니다."));

        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException(("없는 일정입니다.")));

        Comment comment = new Comment(
                user,
                schedule,
                request.getContents());

        Comment savedComment = commentRepository.save(comment);

        return new CreateCommentResponse(savedComment);
    }

    @Transactional(readOnly = true)
    public List<GetCommentResponse> getAllComments(Long scheduleId) {

        List<Comment> comments = commentRepository.findByScheduleId(scheduleId);

        List<GetCommentResponse> dtos = new ArrayList<>();

        for (Comment comment : comments) {
            dtos.add(new GetCommentResponse(
                    comment.getCommentId(),
                    comment.getUser().getUserName(),
                    comment.getContents(),
                    comment.getCreatedAt(),
                    comment.getModifiedAt()));
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public GetCommentResponse getOneComment(Long scheduleId, Long commentId) {

        scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("없는 일정입니다."));

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("없는 댓글입니다."));

        return new GetCommentResponse(
                comment.getCommentId(),
                comment.getUser().getUserName(),
                comment.getContents(),
                comment.getCreatedAt(),
                comment.getModifiedAt());
    }

    public UpdateCommentResponse updateComment(Long scheduleId, Long commentId, UpdateCommentRequest request) {

        scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("없는 일정입니다."));

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("없는 댓글입니다."));

        comment.updateComment(
                request.getContents());

        return new UpdateCommentResponse(
                comment.getCommentId(),
                comment.getUser().getUserName(),
                comment.getContents(),
                comment.getCreatedAt(),
                comment.getModifiedAt());
    }

    @Transactional
    public void deleteComment(Long scheduleId, Long commentId) {

        scheduleRepository.findById(scheduleId).orElseThrow(() -> new IllegalArgumentException("없는 일정입니다."));

        commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("없는 일정입니다."));

        commentRepository.deleteById(commentId);
    }
}
