package com.scheduleapp.Service;

import com.scheduleapp.DTO.CreateScheduleResponse;
import com.scheduleapp.DTO.CreateScheduleRequest;
import com.scheduleapp.Entity.Schedule;
import com.scheduleapp.Repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
