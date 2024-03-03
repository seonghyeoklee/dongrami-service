package com.dongrami.todo.application;

import com.dongrami.exception.BaseException;
import com.dongrami.exception.ErrorCode;
import com.dongrami.todo.domain.TodoEntity;
import com.dongrami.todo.domain.TodoNotificationEntity;
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

import java.time.LocalDate;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoWriteService {
    private final UserService userService;
    private final TodoRepository todoRepository;
    private final TodoRememberRepository todoRememberRepository;

    public ResponseTodoDto createTodo(String userUniqueId, RequestCreateTodoDto request) {

        // 1. 사용자 조회
        UserEntity userEntity = userService.getUser(userUniqueId);

        // 2-1 알림이 존재하지 않는 경우
        if(request.getNotificationTime() == null) {
            TodoEntity todoEntity = TodoEntity.create(
                    request.getContent(),
                    request.getMemo(),
                    TodoStatus.TODO,
                    userEntity
            );

            return ResponseTodoDto.from(todoRepository.save(todoEntity));
        }

        // 2-2 알림이 존재하는 경우
        TodoNotificationEntity todoNotificationEntity = TodoNotificationEntity.builder()
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .notificationTime(request.getNotificationTime())
                .excludedDates(null)
                .userEntity(userEntity)
                .build();

        TodoEntity todoEntity = TodoEntity.create(
                request.getContent(),
                request.getMemo(),
                TodoStatus.TODO,
                userEntity
        );

        todoEntity.addTodoNotification(todoNotificationEntity);

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

        // 1. 사용자 조회
        UserEntity userEntity = userService.getUser(userUniqueId);

        // 2. To-do 조회
        TodoEntity todoEntity = todoRepository.findById(todoId)
                .orElseThrow(() -> new BaseException(ErrorCode.NO_CONTENT));

        // 3. TodoRemember 생성
        TodoRememberEntity todoRememberEntity = TodoRememberEntity.builder()
                .userEntity(userEntity)
                .todoEntity(todoEntity)
                .isDeleted(false)
                .build();

        todoRememberRepository.save(todoRememberEntity);
    }

}
