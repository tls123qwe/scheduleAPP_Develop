package com.example.scheduleapp_develop.service;

import com.example.scheduleapp_develop.dto.scheduleDto.*;
import com.example.scheduleapp_develop.entity.Schedule;
import com.example.scheduleapp_develop.entity.User;
import com.example.scheduleapp_develop.repository.ScheduleRepository;
import com.example.scheduleapp_develop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    public CreateScheduleResponse createSchedule(CreateScheduleRequest request) {

        User user = userRepository.findById(request.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("없는 유저입니다."));

        Schedule schedule = new Schedule(
                user,
                request.getTitle(),
                request.getContents(),
                request.getPassword());

        Schedule savedschedule = scheduleRepository.save(schedule);

        return new CreateScheduleResponse(
                savedschedule.getId(),
                savedschedule.getUser().getUserName(),
                savedschedule.getTitle(),
                savedschedule.getContents(),
                savedschedule.getCreatedAt(),
                savedschedule.getModifiedAt());
    }

    @Transactional(readOnly = true)
    public GetScheduleResponse getOneSchedule(Long Id) {

        Schedule schedule = scheduleRepository.findById(Id).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다."));

        return new GetScheduleResponse(
                schedule.getId(),
                schedule.getUser().getUserName(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt());
    }

    @Transactional(readOnly = true)
    public List<GetScheduleResponse> getAllSchedules() {

        List<Schedule> schedules = scheduleRepository.findAll();

        List<GetScheduleResponse> dtos = new ArrayList<>();

        for (Schedule schedule : schedules) {
            dtos.add(new GetScheduleResponse(
                    schedule.getId(),
                    schedule.getUser().getUserName(),
                    schedule.getTitle(),
                    schedule.getContents(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()));
        }
        return dtos;
    }

    @Transactional
    public UpdateScheduleResponse updateSchedule(Long Id, UpdateScheduleRequest request) {

        User user = userRepository.findById(request.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("없는 유저 입니다."));

        Schedule schedule = scheduleRepository.findById(Id).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다."));

        if (!schedule.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        schedule.updateSchedule(
                user,
                request.getTitle(),
                request.getContents());

        return new UpdateScheduleResponse(
                schedule.getId(),
                schedule.getUser().getUserName(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt());
    }

    @Transactional
    public void deleteSchedule(Long Id, DeleteScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(Id).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다."));

        if (!schedule.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        scheduleRepository.deleteById(Id);
    }
}
