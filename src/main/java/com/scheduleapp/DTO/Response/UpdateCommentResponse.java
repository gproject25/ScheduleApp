package com.scheduleapp.DTO.Response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class UpdateCommentResponse {
    private final Long id;
    private final String contents;
    private final String author;
    private final Long scheduleId;
    private final LocalDateTime createAt;
    private final LocalDateTime modifiedAt;
}
