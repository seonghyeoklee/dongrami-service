package com.dongrami.todo.application;

import com.dongrami.exception.BaseException;
import com.dongrami.exception.ErrorCode;
import com.dongrami.todo.domain.TodoEntity;
import com.dongrami.todo.domain.TodoRememberEntity;
import com.dongrami.todo.dto.response.ResponseTodoDto;
import com.dongrami.todo.repository.TodoRememberRepository;
import com.dongrami.todo.repository.TodoRepository;
import com.dongrami.todo.repository.support.TodoSearchDto;
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
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TodoReadService {
    private final UserService userService;
    private final TodoRepository todoRepository;
    private final TodoRememberRepository todoRememberRepository;

    public ResponseTodoDto getTodoById(Long id) {
        TodoEntity todoEntity = todoRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.NO_CONTENT));

        return ResponseTodoDto.from(todoEntity);
    }

    public Page<ResponseTodoDto> getTodoPageBySearch(Pageable pageable, TodoSearchDto todoSearchDto) {
        Page<TodoEntity> todoRepositoryBySearch = todoRepository.findBySearch(pageable, todoSearchDto);

        return todoRepositoryBySearch
                .map(ResponseTodoDto::from);
    }

    public double getTodoAchievementRate(String userUniqueId, LocalDate currentDate) {

        // 1. 사용자 조회
        UserEntity userEntity = userService.getUserByUserUniqueId(userUniqueId);

        // 2. 사용자의 해당 일의 모든 할일 조회
        List<TodoEntity> todoEntities = todoRepository.findByUserEntityAndCreatedDateTimeAndIsDeletedFalse(userEntity, currentDate.atStartOfDay(), currentDate.plusDays(1).atStartOfDay());

        // 3. 사용자의 모든 할일 중 완료된 할일 조회
        long completedTodoCount = todoEntities.stream()
                .filter(TodoEntity::isCompleted)
                .count();

        // 4. 완료된 할일의 개수 / 모든 할일의 개수 * 100
        if(!todoEntities.isEmpty()) {
            return (double) completedTodoCount / todoEntities.size() * 100;
        }

        return 0;
    }

    public List<ResponseTodoDto> getTodoRemember(String username) {

        // 1. 사용자 조회
        UserEntity userEntity = userService.getUserByUserUniqueId(username);

        // 2. 사용자의 저장된 할 일 조회
        List<TodoRememberEntity> todoRememberEntities = todoRememberRepository.findByUserEntityAndIsDeletedFalse(userEntity);

        return todoRememberEntities.stream()
                .map(entity -> ResponseTodoDto.from(entity.getTodoEntity()))
                .toList();
    }

}
