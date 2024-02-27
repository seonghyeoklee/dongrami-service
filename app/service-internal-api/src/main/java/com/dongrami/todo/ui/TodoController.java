package com.dongrami.todo.ui;

import com.dongrami.common.ApiResponse;
import com.dongrami.todo.application.TodoReadService;
import com.dongrami.todo.application.TodoWriteService;
import com.dongrami.todo.dto.request.RequestCreateTodoDto;
import com.dongrami.todo.dto.request.RequestUpdateTodoDto;
import com.dongrami.todo.dto.response.ResponseTodoAchievementRateDto;
import com.dongrami.todo.dto.response.ResponseTodoDto;
import com.dongrami.todo.repository.support.TodoSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TodoController implements TodoControllerInterface {
    private final TodoWriteService todoWriteService;
    private final TodoReadService todoReadService;

    @GetMapping("/todos")
    public ResponseEntity<?> getTodoPage(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
            TodoSearchDto todoSearchDto
    ) {
        Page<ResponseTodoDto> responses = todoReadService.getTodoPageBySearch(pageable, todoSearchDto);

        return ResponseEntity.ok().body(
                ApiResponse.success(responses)
        );
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<?> getTodoById(@PathVariable Long id) {
        ResponseTodoDto response = todoReadService.getTodoById(id);

        return ResponseEntity.ok().body(
                ApiResponse.success(response)
        );
    }

    @PostMapping("/todos")
    public ResponseEntity<?> createTodo(@AuthenticationPrincipal User principal,
                                        @Valid @RequestBody RequestCreateTodoDto requestCreateTodoDto
    ) {
        ResponseTodoDto response = todoWriteService.createTodo(principal.getUsername(), requestCreateTodoDto);

        return ResponseEntity.ok().body(
                ApiResponse.success(response)
        );
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<?> updateTodo(@PathVariable Long id, @Valid @RequestBody RequestUpdateTodoDto requestUpdateTodoDto) {
        todoWriteService.updateTodo(id, requestUpdateTodoDto);

        return ResponseEntity.ok().body(
                ApiResponse.success()
        );
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable Long id) {
        todoWriteService.deleteTodo(id);

        return ResponseEntity.ok().body(
                ApiResponse.success()
        );
    }

    @GetMapping("/todos/achievement-rate")
    public ResponseEntity<?> getTodoAchievementRate(@AuthenticationPrincipal User principal) {
        double achievementRate = todoReadService.getTodoAchievementRate(principal.getUsername(), LocalDate.now());

        return ResponseEntity.ok().body(
                ApiResponse.success(ResponseTodoAchievementRateDto.from(achievementRate))
        );
    }

    @GetMapping("/todos/remember")
    public ResponseEntity<?> getTodoRemember(@AuthenticationPrincipal User principal) {
        List<ResponseTodoDto> responses = todoReadService.getTodoRemember(principal.getUsername());

        return ResponseEntity.ok().body(
                ApiResponse.success(responses)
        );
    }

}
