package com.scheduleapp.Entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String title;

    @Column(nullable = false, length = 200)
    private String contents;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String password;


    public Schedule(String title, String contents, String author, String password){
        this.title = title;
        this.contents = contents;
        this.author = author;
        this.password = password;
    }

    public void update(String title, String author){
        this.title = title;
        this.author = author;
    }
}
