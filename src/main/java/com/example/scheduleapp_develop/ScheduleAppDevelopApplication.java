package com.example.scheduleapp_develop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ScheduleAppDevelopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleAppDevelopApplication.class, args);
    }

}
