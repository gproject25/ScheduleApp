package com.scheduleapp.Entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name="comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String contents;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String password;

    //schedulId 컬럼에 연결된 일정 ID를 저장
    @Column(name = "schedule_id", nullable = false)
    private Long scheduleId;

    public Comment(String contents, String author, String password, Long scheduleId) {
        this.contents = contents;
        this.author = author;
        this.password = password;
        this.scheduleId = scheduleId;
    }

    public void update(String contents, String author){
        this.contents = contents;
        this.author = author;
    }
}
