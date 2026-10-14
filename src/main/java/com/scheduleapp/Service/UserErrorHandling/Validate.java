package com.scheduleapp.Service.UserErrorHandling;

import org.springframework.stereotype.Component;

@Component
public class Validate {
    public void validateSchedule(String title, String contents, String author, String password, boolean updateCheck) {
        //title
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("일정 제목은 필수 입력값입니다.");
        }
        if (title.length() > 30) {
            throw new IllegalArgumentException("일정 제목은 최대 30자까지 입력할 수 있습니다.");
        }

        //content
        if(!updateCheck) { //update request dto는 contents 필드 값이 없음
            if (contents == null || contents.isBlank()) {
                throw new IllegalArgumentException("일정 내용은 필수 입력값입니다.");
            }
            if (contents.length() > 200) {
                throw new IllegalArgumentException("일정 내용은 최대 200자까지 입력할 수 있습니다.");
            }
        }

        //author
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("작성자는 필수 입력값입니다.");
        }

        //password
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("비밀번호는 필수 입력값입니다.");
        }
    }

    public void validateComment(String contents, String author, String password){
        //content
        if (contents == null || contents.isBlank()) {
            throw new IllegalArgumentException("댓글 내용은 필수 입력값입니다.");
        }
        if (contents.length() > 100) {
            throw new IllegalArgumentException("댓글 내용은 최대 100자까지 입력할 수 있습니다.");
        }

        //author
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("작성자는 필수 입력값입니다.");
        }

        //password
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("비밀번호는 필수 입력값입니다.");
        }
    }


}
