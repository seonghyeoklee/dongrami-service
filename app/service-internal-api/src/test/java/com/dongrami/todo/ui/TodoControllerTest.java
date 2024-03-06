package com.dongrami.todo.ui;

import com.dongrami.AcceptanceTest;
import com.dongrami.todo.dto.request.RequestCreateTodoDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@DisplayName("할 일 관련 기능")
class TodoControllerTest extends AcceptanceTest {

    @DisplayName("할 일을 생성한다.")
    @Test
    void createTodo() {
        RequestCreateTodoDto request = new RequestCreateTodoDto();
        request.setContent("test content!");
        request.setMemo("test memo!");
        request.setNotificationDateTime(LocalDateTime.of(2024, 3, 23, 22, 10, 30));

        given().log().all()
                .auth().oauth2("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMTA4MDQ5MzU3NzE4NDMzNTYwOTQiLCJyb2xlIjoiUk9MRV9VU0VSIn0.LPAlvZel7i79nDad3NvmpphUHv1Pmr5A_tgXNriwYTQ")
                .body(request)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/api/v1/todos")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("data.content", equalTo("test content!"))
                .body("data.memo", equalTo("test memo!"))
                .body("data.todoStatus", equalTo("NOT_COMPLETED"));
    }

    @DisplayName("사용자의 하루동안 달성률을 조회한다.")
    @Test
    void getTodoAchievementRate() {
        createTodo();

        given().log().all()
                .auth().oauth2("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMTA4MDQ5MzU3NzE4NDMzNTYwOTQiLCJyb2xlIjoiUk9MRV9VU0VSIn0.LPAlvZel7i79nDad3NvmpphUHv1Pmr5A_tgXNriwYTQ")
                .when().get("/api/v1/todos/achievement-rate")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("data.achievementRate", equalTo("0.0"));
    }

}
