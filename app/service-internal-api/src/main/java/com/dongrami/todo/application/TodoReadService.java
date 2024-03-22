package com.dongrami.todo.application;

import com.dongrami.exception.BaseException;
import com.dongrami.exception.ErrorCode;
import com.dongrami.todo.domain.TodoEntity;
import com.dongrami.todo.domain.TodoRememberEntity;
import com.dongrami.todo.dto.response.ResponseTodoDto;
import com.dongrami.todo.repository.TodoRememberRepository;
import com.dongrami.todo.repository.TodoRepository;
import com.dongrami.user.application.UserService;
import com.dongrami.user.domain.UserEntity;
import com.dongrami.user.domain.UserGroupEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TodoReadService {
    private final UserService userService;
    private final TodoRepository todoRepository;
    private final TodoRememberRepository todoRememberRepository;

    public ResponseTodoDto getTodoById(String userUniqueId, Long id) {
        UserEntity userEntity = userService.getUserByUserUniqueId(userUniqueId);

        TodoEntity todoEntity = todoRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.TODO_NOT_EXIST));

        if (!todoEntity.isContainsUserIds(getUserIds(userEntity))) {
            throw new BaseException(ErrorCode.TODO_INVALID_AUTHORIZATION);
        }

        return ResponseTodoDto.from(todoEntity);
    }

    public Page<ResponseTodoDto> getTodoPageByCurrentDate(String username, Pageable pageable, LocalDate currentDate) {
        UserEntity userEntity = userService.getUserByUserUniqueId(username);

        return todoRepository.findTodoPageByCurrentDate(pageable, currentDate, getUserIds(userEntity))
                .map(ResponseTodoDto::from);
    }

    private List<Long> getUserIds(UserEntity userEntity) {
        UserGroupEntity userGroupEntity = userEntity.getUserGroupEntity();
        if (userGroupEntity == null) {
            return List.of(userEntity.getId());
        }

        List<UserEntity> userEntities = userGroupEntity.getUserEntities();
        return userEntities.stream()
                .map(UserEntity::getId)
                .toList();
    }

    public int getTodoAchievementRate(String userUniqueId, LocalDate currentDate) {
        UserEntity userEntity = userService.getUserByUserUniqueId(userUniqueId);

        List<TodoEntity> todoEntities = todoRepository.findByUserEntityAndCreatedDateTimeAndIsDeletedFalse(currentDate, getUserIds(userEntity));

        long completedTodoCount = todoEntities.stream()
                .filter(TodoEntity::isCompleted)
                .count();

        if(!todoEntities.isEmpty()) {
            return (int) Math.ceil((double) completedTodoCount / todoEntities.size() * 100);
        }

        return 0;
    }

    public List<ResponseTodoDto> getTodoRemember(String username) {
        UserEntity userEntity = userService.getUserByUserUniqueId(username);

        List<TodoRememberEntity> todoRememberEntities = todoRememberRepository.findByUserIdsAndIsDeletedFalse(getUserIds(userEntity));

        return todoRememberEntities.stream()
                .map(entity -> ResponseTodoDto.from(entity.getTodoEntity()))
                .toList();
    }

}
