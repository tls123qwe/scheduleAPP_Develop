package com.example.scheduleapp_develop.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private String author;
    private String title;
    private String contents;
    private String password;

    public Schedule(String author, String title, String contents, String password) {
        this.author = author;
        this.title = title;
        this.contents = contents;
        this.password = password;
    }

    public void updateSchedule(String author, String title, String contents) {
        this.author = author;
        this.title = title;
        this.contents = contents;
    }
}
