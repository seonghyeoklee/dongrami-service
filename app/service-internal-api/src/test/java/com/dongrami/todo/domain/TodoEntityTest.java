package com.dongrami.todo.domain;

import com.dongrami.user.domain.ProviderType;
import com.dongrami.user.domain.RoleType;
import com.dongrami.user.domain.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Todo 엔티티 테스트")
class TodoEntityTest {
    private UserEntity userEntity;

    @BeforeEach
    void setUp() {
        userEntity = UserEntity.createUser("dongrami", "password", "test@gmail.com", "Y", "EMPTY", ProviderType.KAKAO, RoleType.USER);
    }

    @DisplayName("할 일을 생성한다.")
    @Test
    void createTodo() {

        // given, when
        TodoEntity todoEntity = TodoEntity.create("할 일", "메모", LocalDateTime.now(), TodoStatus.NOT_COMPLETED, userEntity);

        // then
        assertThat(todoEntity.getContent()).isEqualTo("할 일");
        assertThat(todoEntity.getMemo()).isEqualTo("메모");
        assertThat(todoEntity.getTodoStatus()).isEqualTo(TodoStatus.NOT_COMPLETED);
        assertThat(todoEntity.getUserEntity()).isEqualTo(userEntity);
    }

}