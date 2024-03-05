package com.dongrami;

import com.dongrami.todo.repository.TodoRememberRepository;
import com.dongrami.todo.repository.TodoRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
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
@ActiveProfiles("test")
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
