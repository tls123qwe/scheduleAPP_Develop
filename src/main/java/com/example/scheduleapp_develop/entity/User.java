package com.example.scheduleapp_develop.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    private String userName;
    protected String email;

    public User(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public void updateUser(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }
}
