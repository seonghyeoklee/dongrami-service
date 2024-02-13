package com.dongrami.todo.application;

import com.dongrami.todo.domain.TodoEntity;
import com.dongrami.todo.dto.request.RequestCreateTodoDto;
import com.dongrami.todo.dto.request.RequestUpdateTodoDto;
import com.dongrami.todo.dto.response.ResponseTodoDto;
import com.dongrami.todo.repository.TodoRepository;
import com.dongrami.todo.repository.support.TodoSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public void createTodo(RequestCreateTodoDto requestCreateTodoDto) {
        todoRepository.save(requestCreateTodoDto.toEntity());
    }

    public void updateTodo(Long id, RequestUpdateTodoDto requestUpdateTodoDto) {
        TodoEntity todoEntity = todoRepository.findById(id)
                .orElseThrow();

        todoEntity.update(
                requestUpdateTodoDto.getTitle(),
                requestUpdateTodoDto.getContent(),
                requestUpdateTodoDto.getTodoStatus()
        );
    }

    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public ResponseTodoDto getTodoById(Long id) {
        TodoEntity todoEntity = todoRepository.findById(id)
                .orElseThrow();

        return ResponseTodoDto.from(todoEntity);
    }

    @Transactional(readOnly = true)
    public Page<ResponseTodoDto> getTodoPageBySearch(Pageable pageable, TodoSearchDto todoSearchDto) {
        Page<TodoEntity> todoRepositoryBySearch = todoRepository.findBySearch(pageable, todoSearchDto);

        return todoRepositoryBySearch
                .map(ResponseTodoDto::from);
    }

}
