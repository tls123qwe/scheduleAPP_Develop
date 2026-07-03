package com.example.scheduleapp_develop.controller;


import com.example.scheduleapp_develop.dto.scheduleDto.*;
import com.example.scheduleapp_develop.service.ScheduleService;
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
    public ResponseEntity<CreateScheduleResponse> createSchedule(@RequestBody CreateScheduleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.createSchedule(request));
    }

    @GetMapping("/schedules/{Id}")
    public ResponseEntity<GetScheduleResponse> getOneSchedule(@PathVariable Long Id) {
        return ResponseEntity.status(HttpStatus.OK).body((scheduleService.getOneSchedule(Id)));
    }

    @GetMapping("/schedules")
    public ResponseEntity<List<GetScheduleResponse>> getAllSchedules() {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getAllSchedules());
    }

    @PutMapping("/schedules/{Id}")
    public ResponseEntity<UpdateScheduleResponse> updateSchedule(@PathVariable Long Id,
                                                                 @RequestBody UpdateScheduleRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.updateSchedule(Id, request));
    }

    @DeleteMapping("/schedules/{Id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long Id,
                                               @RequestBody DeleteScheduleRequest request) {
        scheduleService.deleteSchedule(Id, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
