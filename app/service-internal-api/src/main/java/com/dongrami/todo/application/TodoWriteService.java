package com.dongrami.todo.application;

import com.dongrami.emoji.domain.EmojiEntity;
import com.dongrami.emoji.repository.EmojiRepository;
import com.dongrami.exception.BaseException;
import com.dongrami.exception.ErrorCode;
import com.dongrami.todo.domain.TodoEmojiEntity;
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

import java.time.LocalDate;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoWriteService {
    private final UserService userService;
    private final TodoRepository todoRepository;
    private final TodoRememberRepository todoRememberRepository;
    private final EmojiRepository emojiRepository;

    public ResponseTodoDto createTodo(String userUniqueId, RequestCreateTodoDto request) {
        UserEntity userEntity = userService.getUserByUserUniqueId(userUniqueId);

        TodoEntity todoEntity = TodoEntity.create(
                request.getContent(),
                request.getMemo(),
                request.getNotificationDateTime(),
                TodoStatus.NOT_COMPLETED,
                userEntity
        );

        return ResponseTodoDto.from(todoRepository.save(todoEntity));
    }

    public void updateTodo(String userUniqueId, Long todoId, RequestUpdateTodoDto requestUpdateTodoDto) {
        UserEntity userEntity = userService.getUserByUserUniqueId(userUniqueId);

        TodoEntity todoEntity = todoRepository.findById(todoId)
                .orElseThrow(() -> new BaseException(ErrorCode.NO_CONTENT));

        todoEntity.update(
                userEntity,
                requestUpdateTodoDto.getContent(),
                requestUpdateTodoDto.getTodoStatus()
        );
    }

    public void deleteTodo(String userUniqueId, Long todoId) {
        UserEntity userEntity = userService.getUserByUserUniqueId(userUniqueId);

        TodoEntity todoEntity = todoRepository.findById(todoId)
                .orElseThrow(() -> new BaseException(ErrorCode.NO_CONTENT));

        todoEntity.delete(userEntity);
    }

    public void createTodoRemember(String userUniqueId, Long todoId) {
        UserEntity userEntity = userService.getUserByUserUniqueId(userUniqueId);

        TodoEntity todoEntity = todoRepository.findById(todoId)
                .orElseThrow(() -> new BaseException(ErrorCode.NO_CONTENT));

        TodoRememberEntity todoRememberEntity = TodoRememberEntity.builder()
                .userEntity(userEntity)
                .todoEntity(todoEntity)
                .isDeleted(false)
                .build();

        todoRememberRepository.save(todoRememberEntity);
    }

    public void changeTodoStatus(String userUniqueId, Long todoId) {
        UserEntity userEntity = userService.getUserByUserUniqueId(userUniqueId);

        TodoEntity todoEntity = todoRepository.findById(todoId)
                .orElseThrow(() -> new BaseException(ErrorCode.NO_CONTENT));

        todoEntity.changeTodoStatus(userEntity);
    }

    public void changeTodoPinned(String userUniqueId, Long todoId, boolean isPinned) {
        UserEntity userEntity = userService.getUserByUserUniqueId(userUniqueId);

        TodoEntity todoEntity = todoRepository.findById(todoId)
                .orElseThrow(() -> new BaseException(ErrorCode.NO_CONTENT));

        todoEntity.changeTodoPinned(userEntity, isPinned);
    }

    public void copyTodoToNextDay(String userUniqueId, Long todoId, LocalDate currentDate) {
        UserEntity userEntity = userService.getUserByUserUniqueId(userUniqueId);

        TodoEntity todoEntity = todoRepository.findById(todoId)
                .orElseThrow(() -> new BaseException(ErrorCode.NO_CONTENT));

        if (!todoEntity.isOwner(userEntity)) {
            throw new BaseException(ErrorCode.HANDLE_ACCESS_DENIED);
        }

        TodoEntity copyTodoEntity = TodoEntity.create(
                todoEntity.getContent(),
                todoEntity.getMemo(),
                currentDate.plusDays(1).atStartOfDay(),
                TodoStatus.NOT_COMPLETED,
                userEntity
        );

        todoRepository.save(copyTodoEntity);
    }

    public void createTodoEmoji(String username, Long todoId, Long emojiId) {
        UserEntity userEntity = userService.getUserByUserUniqueId(username);

        TodoEntity todoEntity = todoRepository.findById(todoId)
                .orElseThrow(() -> new BaseException(ErrorCode.NO_CONTENT));

        EmojiEntity emojiEntity = emojiRepository.findById(emojiId)
                .orElseThrow(() -> new BaseException(ErrorCode.NO_CONTENT));

        TodoEmojiEntity todoEmojiEntity = TodoEmojiEntity.builder()
                .emojiEntity(emojiEntity)
                .todoEntity(todoEntity)
                .userEntity(userEntity)
                .build();

        todoEntity.addTodoEmoji(todoEmojiEntity);
    }

}
