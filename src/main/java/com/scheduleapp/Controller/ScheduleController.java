package com.scheduleapp.Controller;

import com.scheduleapp.DTO.CreateScheduleResponse;
import com.scheduleapp.DTO.CreateScheduleRequest;
import com.scheduleapp.DTO.GetScheduleResponse;
import com.scheduleapp.Service.ScheduleService;
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
    public ResponseEntity<CreateScheduleResponse> createSchedule(@RequestBody CreateScheduleRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.createScehdule(request));
    }

    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<GetScheduleResponse> getOneSchedule(@PathVariable Long scheduleId){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getOneSchedule(scheduleId));
    }

    @GetMapping("/schedules")
    public ResponseEntity<List<GetScheduleResponse>> getAllSchedules(@RequestParam(required = false) String author){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getAllSchedules(author));
    }
}
