package com.dongrami.todo.application;

import com.dongrami.todo.domain.TodoEntity;
import com.dongrami.todo.dto.request.RequestTodoDto;
import com.dongrami.todo.dto.response.ResponseTodoDto;
import com.dongrami.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public void createTodo(RequestTodoDto requestTodoDto) {
        todoRepository.save(requestTodoDto.toEntity());
    }

    public void updateTodo(Long id) {

    }

    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public ResponseTodoDto getTodo(Long id) {
        TodoEntity todoEntity = todoRepository.findById(id)
                .orElseThrow();

        return ResponseTodoDto.from(todoEntity);
    }

    @Transactional(readOnly = true)
    public List<RequestTodoDto> getTodoList() {

        return null;
    }
}
