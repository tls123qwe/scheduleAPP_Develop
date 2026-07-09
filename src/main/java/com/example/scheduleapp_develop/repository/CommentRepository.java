package com.example.scheduleapp_develop.repository;

import com.example.scheduleapp_develop.entity.Comment;
import com.example.scheduleapp_develop.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    //
    List<Comment> findByScheduleId(Long scheduleId);
}
