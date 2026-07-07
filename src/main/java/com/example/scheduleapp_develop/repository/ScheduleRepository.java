package com.example.scheduleapp_develop.repository;

import com.example.scheduleapp_develop.entity.Schedule;
import com.example.scheduleapp_develop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findByUserUserId(Long userId);

    Long user(User user);
}
