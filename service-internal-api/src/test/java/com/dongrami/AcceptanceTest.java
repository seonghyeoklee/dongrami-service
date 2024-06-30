package com.dongrami;

import com.dongrami.todo.repository.TodoRememberRepository;
import com.dongrami.todo.repository.TodoRepository;
import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@AutoConfigureMockMvc
@ActiveProfiles("local")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AcceptanceTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private TodoRememberRepository todoRememberRepository;

    @LocalServerPort
    int port;

    @BeforeAll
    public static void beforeAll() {
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
        System.setProperty("NAVER_REDIRECT_URL", dotenv.get("NAVER_REDIRECT_URL"));
        System.setProperty("KAKAO_CLIENT_ID", dotenv.get("KAKAO_CLIENT_ID"));
        System.setProperty("KAKAO_CLIENT_SECRET", dotenv.get("KAKAO_CLIENT_SECRET"));
        System.setProperty("KAKAO_REDIRECT_URL", dotenv.get("KAKAO_REDIRECT_URL"));
    }

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @AfterEach
    void afterEach() {
        todoRememberRepository.deleteAllInBatch();
        todoRepository.deleteAllInBatch();
    }

}
