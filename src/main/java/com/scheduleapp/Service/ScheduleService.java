package com.scheduleapp.Service;

import com.scheduleapp.DTO.CreateScheduleResponse;
import com.scheduleapp.DTO.CreateScheduleRequest;
import com.scheduleapp.DTO.GetScheduleResponse;
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
        Schedule schedule = new Schedule(request.getTitle(), request.getContents(),request.getAuthor(),request.getPassword());
        scheduleRepository.save(schedule);
        return new CreateScheduleResponse(schedule.getId(), schedule.getTitle(), schedule.getContents(),schedule.getAuthor(), schedule.getCreatedAt(), schedule.getModifiedAt());
    }

    @Transactional(readOnly = true)
    public GetScheduleResponse getOneSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("일정이 없습니다."));
        return new GetScheduleResponse(schedule.getId(), schedule.getTitle(), schedule.getContents(), schedule.getAuthor(),schedule.getCreatedAt(), schedule.getModifiedAt());
    }

    @Transactional(readOnly = true)
    public List<GetScheduleResponse> getAllSchedules(String author) {
        List<Schedule> schedules;
        if(author != null && !author.isBlank()){
            schedules = scheduleRepository.findByAuthor(author);
        }
        else{
            schedules = scheduleRepository.findAll();
        }
        List<GetScheduleResponse> list = schedules.stream()
                .map(schedule -> new GetScheduleResponse(schedule.getId(), schedule.getTitle(), schedule.getContents(), schedule.getAuthor(),schedule.getCreatedAt(), schedule.getModifiedAt()))
                .toList();
        return list;
    }
}
