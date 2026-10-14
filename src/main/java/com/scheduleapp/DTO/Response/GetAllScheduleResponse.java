package com.scheduleapp.DTO.Response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class GetAllScheduleResponse {
    private final Long id;
    private final String title;
    private final String contents;
    private final String author;
    private final LocalDateTime createAt;
    private final LocalDateTime modifiedAt;
}