package com.example.scheduleapp_develop.service;

import com.example.scheduleapp_develop.dto.scheduleDto.*;
import com.example.scheduleapp_develop.entity.Schedule;
import com.example.scheduleapp_develop.entity.User;
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
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    public CreateScheduleResponse createSchedule(CreateScheduleRequest request,
                                                 HttpServletRequest servletRequest) {

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
    public UpdateScheduleResponse updateSchedule(Long Id,
                                                 UpdateScheduleRequest request,
                                                 HttpServletRequest servletRequest) {

        // 기존 세션 호출 없으면 null
        HttpSession session = servletRequest.getSession(false);

        // 세션이 없다 = 로그인 하지 않음
        // 세션값이 null이거나 로그인 정보가 null이면 예외 처리
        if (session == null || session.getAttribute("LOGIN_USER") == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }

        // 로그인 시 저장해둔 유저ID 가져오기
        Long loginUserId = (Long) session.getAttribute("LOGIN_USER");

        // 가져온 유저ID로 DB에서 일정 조회
        Schedule schedule = scheduleRepository.findById(Id).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다."));

        // 작성자와 로그인 유저 비교하기
        if (!schedule.getUser().getUserId().equals(loginUserId)){
            throw new IllegalArgumentException("작성자만 수정할 수 없습니다.");
        }

        // 비밀번호 비교
        if (!schedule.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        schedule.updateSchedule(
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

    @Transactional(readOnly = true)
    public List<GetUserScheduleResponse> getUserSchedules(Long userId) {
        List<Schedule> schedules = scheduleRepository.findByUserUserId(userId);

        List<GetUserScheduleResponse> dtos = new ArrayList<>();

        for (Schedule schedule : schedules) {
            dtos.add(new GetUserScheduleResponse(
                    schedule.getId(),
                    schedule.getUser().getUserName(),
                    schedule.getTitle(),
                    schedule.getContents(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()));
        }
        return dtos;
    }
}
