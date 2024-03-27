package com.dongrami;

import com.dongrami.config.properties.AppProperties;
import com.dongrami.config.properties.CorsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableConfigurationProperties({
        CorsProperties.class,
        AppProperties.class
})
@EnableAsync
@EnableJpaAuditing
@EnableWebSecurity
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
