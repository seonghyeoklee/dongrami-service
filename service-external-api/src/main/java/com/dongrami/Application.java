package com.dongrami;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        String activeProfile = System.getProperty("spring.profiles.active");

        if (activeProfile == null) {
            System.setProperty("spring.profiles.active", "local");
        }

        SpringApplication.run(Application.class, args);
    }
}
