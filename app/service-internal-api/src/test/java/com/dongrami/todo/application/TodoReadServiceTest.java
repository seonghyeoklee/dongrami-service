package com.dongrami.todo.application;

import com.dongrami.todo.domain.TodoEntity;
import com.dongrami.todo.dto.response.ResponseTodoDto;
import com.dongrami.todo.repository.TodoRememberRepository;
import com.dongrami.todo.repository.TodoRepository;
import com.dongrami.user.application.UserService;
import com.dongrami.user.domain.UserEntity;
import com.dongrami.user.domain.UserPersonalColor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@DisplayName("TodoReadService Test")
@ExtendWith(MockitoExtension.class)
class TodoReadServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private TodoRepository todoRepository;

    @Mock
    private TodoRememberRepository todoRememberRepository;

    @InjectMocks
    private TodoReadService todoReadService;

    @Test
    void name() {
        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .userUniqueId("testUser")
                .userPersonalColor(UserPersonalColor.builder().color("#000000").build())
                .build();

        TodoEntity todoEntity = TodoEntity.builder()
                .id(1L)
                .content("content")
                .memo("memo")
                .userEntity(userEntity)
                .build();

        given(userService.getUserByUserUniqueId(any()))
                .willReturn(userEntity);

        given(todoRepository.findById(any()))
                .willReturn(Optional.of(todoEntity));

        ResponseTodoDto response = todoReadService.getTodoById("testUser", 1L);

        assertThat(response.getId()).isNotNull();
        assertThat(response.getContent()).isEqualTo("content");
        assertThat(response.getMemo()).isEqualTo("memo");
    }

}