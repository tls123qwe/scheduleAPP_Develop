package com.example.scheduleapp_develop.repository;

import com.example.scheduleapp_develop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
