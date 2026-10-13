package com.scheduleapp.DTO;

import lombok.Getter;

@Getter
public class CreateScheduleRequest {
    String title;
    String contents;
    String author;
    String password;
}
