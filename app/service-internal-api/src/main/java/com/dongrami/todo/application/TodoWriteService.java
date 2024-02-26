package com.dongrami.todo.application;

import com.dongrami.todo.domain.TodoEntity;
import com.dongrami.todo.domain.TodoNotificationEntity;
import com.dongrami.todo.domain.TodoStatus;
import com.dongrami.todo.dto.request.RequestCreateTodoDto;
import com.dongrami.todo.dto.request.RequestUpdateTodoDto;
import com.dongrami.todo.dto.response.ResponseTodoDto;
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

    public void updateTodo(Long id, RequestUpdateTodoDto requestUpdateTodoDto) {
        TodoEntity todoEntity = todoRepository.findById(id)
                .orElseThrow();

        todoEntity.update(
                requestUpdateTodoDto.getContent(),
                requestUpdateTodoDto.getTodoStatus()
        );
    }

    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }

}
