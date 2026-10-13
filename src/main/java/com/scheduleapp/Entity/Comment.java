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
    private String contents;
    private String author;
    private String password;

    @ManyToOne
    @JoinColumn(name="scheduleId", nullable = false)
    private Schedule schedule;

    public Comment(String contents, String author, String password, Schedule schedule) {
        this.contents = contents;
        this.author = author;
        this.password = password;
        this.schedule = schedule;
    }
    
}
