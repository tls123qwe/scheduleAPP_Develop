package com.example.scheduleapp_develop.repository;

import com.example.scheduleapp_develop.entity.Schedule;
import com.example.scheduleapp_develop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    // 유저로 일정을 조회할 때 사용
    List<Schedule> findByUserUserId(Long userId);

}
