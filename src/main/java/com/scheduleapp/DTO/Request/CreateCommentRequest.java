package com.scheduleapp.DTO.Request;

import lombok.Getter;

@Getter
public class CreateCommentRequest {
    private String contents;
    private String author;
    private String password;
}
