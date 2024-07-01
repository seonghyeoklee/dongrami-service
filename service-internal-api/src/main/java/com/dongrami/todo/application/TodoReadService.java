package com.dongrami.todo.application;

import com.dongrami.exception.BaseException;
import com.dongrami.exception.ErrorCode;
import com.dongrami.todo.domain.TodoEntity;
import com.dongrami.todo.domain.TodoRememberEntity;
import com.dongrami.todo.dto.response.ResponseTodoDetail;
import com.dongrami.todo.repository.TodoRememberRepository;
import com.dongrami.todo.repository.TodoRepository;
import com.dongrami.user.application.UserService;
import com.dongrami.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoReadService {
    private final UserService userService;
    private final TodoRepository todoRepository;
    private final TodoRememberRepository todoRememberRepository;

    public ResponseTodoDetail getTodoById(String userUniqueId, Long id) {
        UserEntity userEntity = userService.getUserByUserUniqueId(userUniqueId);

        TodoEntity todoEntity = todoRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.TODO_NOT_EXIST));

        UserEntity todoUserEntity = todoEntity.getAuthorUserEntity();

        // 할일 작성자와 할일을 작성한 사용자가 동일하거나, 페어 사용자인 경우에만 조회 가능
        if (userEntity.isTodoAuthorization(todoUserEntity)) {
            return ResponseTodoDetail.of(todoEntity);
        }

        throw new BaseException(ErrorCode.TODO_INVALID_AUTHORIZATION);
    }

    public Page<ResponseTodoDetail> getTodoPageByCurrentDate(String username, Pageable pageable, LocalDate currentDate) {
        UserEntity userEntity = userService.getUserByUserUniqueId(username);

        return todoRepository.findTodoPageByCurrentDate(pageable, currentDate, userEntity).map(ResponseTodoDetail::of);
    }

    public double getTodoAchievementRate(String userUniqueId, LocalDate currentDate) {
        UserEntity userEntity = userService.getUserByUserUniqueId(userUniqueId);

        List<TodoEntity> todoEntities = todoRepository.findByUserEntityAndCreatedDateTimeAndIsDeletedFalse(currentDate, userEntity);

        long completedTodoCount = todoEntities.stream()
                .filter(TodoEntity::isCompleted)
                .count();

        return Math.ceil((double) completedTodoCount / todoEntities.size() * 100);
    }

    public List<ResponseTodoDetail> getTodoRemember(String username) {
        UserEntity userEntity = userService.getUserByUserUniqueId(username);

        List<TodoRememberEntity> todoRememberEntities = todoRememberRepository.findByUserIdsAndIsDeletedFalse(userEntity);

        return todoRememberEntities.stream()
                .map(entity -> ResponseTodoDetail.of(entity.getTodoEntity()))
                .toList();
    }
}
