package com.scheduleapp.DTO.Request;

import lombok.Getter;

@Getter
public class UpdateCommentRequest {
    private String contents;
    private String author;
    private String password;
}
