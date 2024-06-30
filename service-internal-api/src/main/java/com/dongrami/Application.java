package com.dongrami;

import com.dongrami.config.properties.AppProperties;
import com.dongrami.config.properties.CorsProperties;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
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
@EnableCaching
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        String activeProfile = System.getProperty("spring.profiles.active");

        if (activeProfile == null) {
            System.setProperty("spring.profiles.active", "local");
        }

        Dotenv dotenv = Dotenv.configure().load();
        System.setProperty("MYSQL_URL", dotenv.get("MYSQL_URL"));
        System.setProperty("MYSQL_USERNAME", dotenv.get("MYSQL_USERNAME"));
        System.setProperty("MYSQL_PASSWORD", dotenv.get("MYSQL_PASSWORD"));

        System.setProperty("JWT_SECRET", dotenv.get("JWT_SECRET"));
        System.setProperty("APP_AUTH_TOKEN_SECRET", dotenv.get("APP_AUTH_TOKEN_SECRET"));
        System.setProperty("APP_AUTH_TOKEN_EXPIRY", dotenv.get("APP_AUTH_TOKEN_EXPIRY"));
        System.setProperty("APP_AUTH_REFRESH_TOKEN_EXPIRY", dotenv.get("APP_AUTH_REFRESH_TOKEN_EXPIRY"));
        System.setProperty("AWS_ACCESS_KEY", dotenv.get("AWS_ACCESS_KEY"));
        System.setProperty("AWS_SECRET_KEY", dotenv.get("AWS_SECRET_KEY"));
        System.setProperty("AWS_REGION", dotenv.get("AWS_REGION"));

        System.setProperty("GOOGLE_CLIENT_ID", dotenv.get("GOOGLE_CLIENT_ID"));
        System.setProperty("GOOGLE_CLIENT_SECRET", dotenv.get("GOOGLE_CLIENT_SECRET"));
        System.setProperty("NAVER_CLIENT_ID", dotenv.get("NAVER_CLIENT_ID"));
        System.setProperty("NAVER_CLIENT_SECRET", dotenv.get("NAVER_CLIENT_SECRET"));
        System.setProperty("NAVER_REDIRECT_URI", dotenv.get("NAVER_REDIRECT_URI"));
        System.setProperty("KAKAO_CLIENT_ID", dotenv.get("KAKAO_CLIENT_ID"));
        System.setProperty("KAKAO_CLIENT_SECRET", dotenv.get("KAKAO_CLIENT_SECRET"));
        System.setProperty("KAKAO_REDIRECT_URI", dotenv.get("KAKAO_REDIRECT_URI"));

        SpringApplication.run(Application.class, args);
    }
}
