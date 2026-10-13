package com.scheduleapp.Service;

import com.scheduleapp.DTO.*;
import com.scheduleapp.Entity.Schedule;
import com.scheduleapp.Repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;


    @Transactional
    public CreateScheduleResponse createScehdule(CreateScheduleRequest request) {
        //일정 생성
        Schedule schedule = new Schedule(request.getTitle(), request.getContents(),request.getAuthor(),request.getPassword());

        //일정 DB에 추가
        scheduleRepository.save(schedule);

        //Create DTO response 반환
        return new CreateScheduleResponse(schedule.getId(), schedule.getTitle(), schedule.getContents(),schedule.getAuthor(), schedule.getCreatedAt(), schedule.getModifiedAt());
    }

    @Transactional(readOnly = true)
    public GetScheduleResponse getOneSchedule(Long scheduleId) {
        //일정 조회
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("일정이 없습니다."));

        //Get DTO response 반환
        return new GetScheduleResponse(schedule.getId(), schedule.getTitle(), schedule.getContents(), schedule.getAuthor(),schedule.getCreatedAt(), schedule.getModifiedAt());
    }

    @Transactional(readOnly = true)
    public List<GetScheduleResponse> getAllSchedules(String author) {
        List<Schedule> schedules;
        //author이 포함되어 있으면 author 기준으로 전체 조회
        if(author != null && !author.isBlank()){
            schedules = scheduleRepository.findByAuthor(author);
        }
        else{   //author이 포함되어 있지 않으면 -> 그냥 전체 조회
            schedules = scheduleRepository.findAll();
        }

        //stream 형식으로 DTO list 반환
        List<GetScheduleResponse> list = schedules.stream()
                .map(schedule -> new GetScheduleResponse(schedule.getId(), schedule.getTitle(), schedule.getContents(), schedule.getAuthor(),schedule.getCreatedAt(), schedule.getModifiedAt()))
                .toList();
        return list;
    }

    @Transactional
    public UpdateScheduleResponse updateSchedule(Long scheduleId, UpdateScheduleRequest request) {
        //일정 조회
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("일정이 없습니다."));

        //비밀번호 확인
        if(!schedule.getPassword().equals(request.getPassword())){
            throw new IllegalStateException("\"비밀번호가 일치하지 않습니다.");
        }

        //update DTO 반환
        schedule.update(request.getTitle(), request.getAuthor());
        return new UpdateScheduleResponse(schedule.getId(), schedule.getTitle(), schedule.getContents(), schedule.getAuthor(),schedule.getCreatedAt(), schedule.getModifiedAt());
    }
}
