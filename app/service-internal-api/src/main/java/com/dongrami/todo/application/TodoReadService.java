package com.dongrami.todo.application;

import com.dongrami.todo.domain.TodoEntity;
import com.dongrami.todo.dto.response.ResponseTodoDto;
import com.dongrami.todo.repository.TodoRepository;
import com.dongrami.todo.repository.support.TodoSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TodoReadService {
    private final TodoRepository todoRepository;

    public ResponseTodoDto getTodoById(Long id) {
        TodoEntity todoEntity = todoRepository.findById(id)
                .orElseThrow();

        return ResponseTodoDto.from(todoEntity);
    }

    public Page<ResponseTodoDto> getTodoPageBySearch(Pageable pageable, TodoSearchDto todoSearchDto) {
        Page<TodoEntity> todoRepositoryBySearch = todoRepository.findBySearch(pageable, todoSearchDto);

        return todoRepositoryBySearch
                .map(ResponseTodoDto::from);
    }

}
