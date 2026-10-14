package com.scheduleapp.Controller;

import com.scheduleapp.DTO.Request.CreateScheduleRequest;
import com.scheduleapp.DTO.Request.UpdateScheduleRequest;
import com.scheduleapp.DTO.Response.CreateScheduleResponse;
import com.scheduleapp.DTO.Response.GetAllScheduleResponse;
import com.scheduleapp.DTO.Response.GetScheduleResponse;
import com.scheduleapp.DTO.Response.UpdateScheduleResponse;
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
    public ResponseEntity<List<GetAllScheduleResponse>> getAllSchedules(@RequestParam(required = false) String author){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getAllSchedules(author));
    }

    @PutMapping("/schedules/{scheduleId}")
    public ResponseEntity<UpdateScheduleResponse> updateSchedule(@PathVariable Long scheduleId, @RequestBody UpdateScheduleRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.updateSchedule(scheduleId,request));
    }

    @DeleteMapping("/schedules/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long scheduleId){
        scheduleService.deleteSchedule(scheduleId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
