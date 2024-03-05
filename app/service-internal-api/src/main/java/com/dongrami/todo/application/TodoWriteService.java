package com.dongrami.todo.application;

import com.dongrami.exception.BaseException;
import com.dongrami.exception.ErrorCode;
import com.dongrami.todo.domain.TodoEntity;
import com.dongrami.todo.domain.TodoRememberEntity;
import com.dongrami.todo.domain.TodoStatus;
import com.dongrami.todo.dto.request.RequestCreateTodoDto;
import com.dongrami.todo.dto.request.RequestUpdateTodoDto;
import com.dongrami.todo.dto.response.ResponseTodoDto;
import com.dongrami.todo.repository.TodoRememberRepository;
import com.dongrami.todo.repository.TodoRepository;
import com.dongrami.user.application.UserService;
import com.dongrami.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoWriteService {
    private final UserService userService;
    private final TodoRepository todoRepository;
    private final TodoRememberRepository todoRememberRepository;

    public ResponseTodoDto createTodo(String userUniqueId, RequestCreateTodoDto request) {
        UserEntity userEntity = userService.getUser(userUniqueId);

        TodoEntity todoEntity = TodoEntity.create(
                request.getContent(),
                request.getMemo(),
                request.getNotificationDateTime(),
                TodoStatus.NOT_COMPLETED,
                userEntity
        );

        return ResponseTodoDto.from(todoRepository.save(todoEntity));
    }

    public void updateTodo(String userUniqueId, Long id, RequestUpdateTodoDto requestUpdateTodoDto) {
        UserEntity userEntity = userService.getUser(userUniqueId);

        TodoEntity todoEntity = todoRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.NO_CONTENT));

        todoEntity.update(
                userEntity,
                requestUpdateTodoDto.getContent(),
                requestUpdateTodoDto.getTodoStatus()
        );
    }

    public void deleteTodo(String userUniqueId, Long id) {
        UserEntity userEntity = userService.getUser(userUniqueId);

        TodoEntity todoEntity = todoRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.NO_CONTENT));

        todoEntity.delete(userEntity);
    }

    public void createTodoRemember(String userUniqueId, Long todoId) {
        UserEntity userEntity = userService.getUser(userUniqueId);

        TodoEntity todoEntity = todoRepository.findById(todoId)
                .orElseThrow(() -> new BaseException(ErrorCode.NO_CONTENT));

        TodoRememberEntity todoRememberEntity = TodoRememberEntity.builder()
                .userEntity(userEntity)
                .todoEntity(todoEntity)
                .isDeleted(false)
                .build();

        todoRememberRepository.save(todoRememberEntity);
    }

    public void changeTodoStatus(String userUniqueId, Long id, TodoStatus todoStatus) {
        UserEntity userEntity = userService.getUser(userUniqueId);

        TodoEntity todoEntity = todoRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.NO_CONTENT));

        todoEntity.changeTodoStatus(userEntity, todoStatus);
    }

    public void changeTodoPinned(String userUniqueId, Long id, boolean isPinned) {
        UserEntity userEntity = userService.getUser(userUniqueId);

        TodoEntity todoEntity = todoRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.NO_CONTENT));

        todoEntity.changeTodoPinned(userEntity, isPinned);
    }

}
