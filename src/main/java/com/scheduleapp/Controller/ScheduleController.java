package com.scheduleapp.Controller;

import com.scheduleapp.DTO.CreateScheduleResponse;
import com.scheduleapp.DTO.CreateScheduleRequest;
import com.scheduleapp.DTO.GetScheduleResponse;
import com.scheduleapp.Service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ResponseEntity<CreateScheduleResponse> createSchedule(@RequestBody CreateScheduleRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.createScehdule(request));
    }

}
