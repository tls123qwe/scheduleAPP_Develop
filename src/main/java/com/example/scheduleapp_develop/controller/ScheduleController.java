package com.example.scheduleapp_develop.controller;


import com.example.scheduleapp_develop.dto.scheduleDto.*;
import com.example.scheduleapp_develop.service.ScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ResponseEntity<CreateScheduleResponse> createSchedule(@RequestBody CreateScheduleRequest request,
                                                                 HttpServletRequest servletRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.createSchedule(request, servletRequest));
    }


    // 단순 일정 단건 조회
    @GetMapping("/schedules/{Id}")
    public ResponseEntity<GetScheduleResponse> getOneSchedule(@PathVariable Long Id) {
        return ResponseEntity.status(HttpStatus.OK).body((scheduleService.getOneSchedule(Id)));
    }

    // 유저별 다건 조회 (유저ID 사용)
    @GetMapping("/schedules/users/{userId}")
    public ResponseEntity<List<GetUserScheduleResponse>> getUserSchedules(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body((scheduleService.getUserSchedules(userId)));
    }

    // 일정 전체 조회
    @GetMapping("/schedules")
    public ResponseEntity<List<GetScheduleResponse>> getAllSchedules() {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getAllSchedules());
    }

    // 일정 수정
    @PutMapping("/schedules/{Id}")
    public ResponseEntity<UpdateScheduleResponse> updateSchedule(@PathVariable Long Id,
                                                                 @RequestBody UpdateScheduleRequest request,
                                                                 HttpServletRequest servletRequest){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.updateSchedule(Id, request, servletRequest));
    }

    // 일정 삭제
    @DeleteMapping("/schedules/{Id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long Id,
                                               @RequestBody DeleteScheduleRequest request) {
        scheduleService.deleteSchedule(Id, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
