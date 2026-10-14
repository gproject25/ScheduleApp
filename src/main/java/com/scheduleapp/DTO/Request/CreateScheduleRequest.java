package com.scheduleapp.DTO.Request;

import lombok.Getter;

@Getter
public class CreateScheduleRequest {
    private String title;
    private String contents;
    private String author;
    private String password;
}
