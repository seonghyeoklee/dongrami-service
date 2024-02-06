package com.dongrami.todo.ui;

import com.dongrami.common.ApiResponse;
import com.dongrami.todo.application.TodoService;
import com.dongrami.todo.dto.request.RequestCreateTodoDto;
import com.dongrami.todo.dto.request.RequestUpdateTodoDto;
import com.dongrami.todo.dto.response.ResponseTodoDto;
import com.dongrami.todo.repository.support.TodoSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TodoController {
    private final TodoService todoService;

    @GetMapping("/todos")
    public ResponseEntity<?> getTodoPage(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
            TodoSearchDto todoSearchDto
    ) {
        Page<ResponseTodoDto> responses = todoService.getTodoPageBySearch(pageable, todoSearchDto);

        return ResponseEntity.ok().body(
                ApiResponse.success(responses)
        );
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<?> getTodoById(@PathVariable Long id) {
        ResponseTodoDto response = todoService.getTodoById(id);

        return ResponseEntity.ok().body(
                ApiResponse.success(response)
        );
    }

    @PostMapping("/todos")
    public ResponseEntity<?> createTodo(@Valid @RequestBody RequestCreateTodoDto requestCreateTodoDto) {
        todoService.createTodo(requestCreateTodoDto);

        return ResponseEntity.ok().body(
                ApiResponse.success()
        );
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<?> updateTodo(@PathVariable Long id, @Valid @RequestBody RequestUpdateTodoDto requestUpdateTodoDto) {
        todoService.updateTodo(id, requestUpdateTodoDto);

        return ResponseEntity.ok().body(
                ApiResponse.success()
        );
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);

        return ResponseEntity.ok().body(
                ApiResponse.success()
        );
    }

}
