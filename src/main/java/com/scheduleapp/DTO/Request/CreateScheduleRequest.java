package com.scheduleapp.DTO.Request;

import lombok.Getter;

@Getter
public class CreateScheduleRequest {
    String title;
    String contents;
    String author;
    String password;
}
