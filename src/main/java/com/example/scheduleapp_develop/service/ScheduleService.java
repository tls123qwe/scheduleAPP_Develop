package com.example.scheduleapp_develop.service;

import com.example.scheduleapp_develop.dto.*;
import com.example.scheduleapp_develop.entity.Schedule;
import com.example.scheduleapp_develop.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public CreateScheduleResponse createSchedule(CreateScheduleRequest request) {
        Schedule schedule = new Schedule(
                request.getAuthor(),
                request.getTitle(),
                request.getContents(),
                request.getPassword());

        Schedule savedschedule = scheduleRepository.save(schedule);

        return new CreateScheduleResponse(
                savedschedule.getId(),
                savedschedule.getAuthor(),
                savedschedule.getTitle(),
                savedschedule.getContents());
    }

    @Transactional(readOnly = true)
    public GetScheduleResponse getOneSchedule(Long Id) {
        Schedule schedule = scheduleRepository.findById(Id).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다."));

        return new GetScheduleResponse(
                schedule.getId(),
                schedule.getAuthor(),
                schedule.getTitle(),
                schedule.getContents());
    }

    @Transactional(readOnly = true)
    public List<GetScheduleResponse> getAllSchedules() {

        List<Schedule> schedules = scheduleRepository.findAll();

        List<GetScheduleResponse> dtos = new ArrayList<>();

        for (Schedule schedule : schedules) {
            dtos.add(new GetScheduleResponse(
                    schedule.getId(),
                    schedule.getAuthor(),
                    schedule.getTitle(),
                    schedule.getContents()));
        }
        return dtos;
    }

    @Transactional
    public UpdateScheduleResponse updateSchedule(Long Id, UpdateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(Id).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다."));

        schedule.updateSchedule(
                request.getAuthor(),
                request.getTitle(),
                request.getContents());

        return new UpdateScheduleResponse(
                schedule.getId(),
                schedule.getAuthor(),
                schedule.getTitle(),
                schedule.getContents());
    }

    @Transactional
    public void deleteSchedule(Long Id, DeleteScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(Id).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다."));

        scheduleRepository.deleteById(Id);
    }
}
