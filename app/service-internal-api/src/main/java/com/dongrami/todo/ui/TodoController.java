package com.dongrami.todo.ui;

import com.dongrami.common.ApiResponse;
import com.dongrami.todo.application.TodoReadService;
import com.dongrami.todo.application.TodoWriteService;
import com.dongrami.todo.dto.request.RequestCreateTodoDto;
import com.dongrami.todo.dto.request.RequestCreateTodoRememberDto;
import com.dongrami.todo.dto.request.RequestUpdateTodoDto;
import com.dongrami.todo.dto.response.ResponseTodoAchievementRateDto;
import com.dongrami.todo.dto.response.ResponseTodoDto;
import com.dongrami.todo.repository.support.TodoSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
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
    public ResponseEntity<?> getTodoPage(Pageable pageable, TodoSearchDto todoSearchDto) {
        Page<ResponseTodoDto> responses = todoReadService.getTodoPageBySearch(pageable, todoSearchDto);

        return ResponseEntity.ok().body(
                ApiResponse.success(responses)
        );
    }

    @GetMapping("/todos/{todoId}")
    public ResponseEntity<?> getTodoById(@AuthenticationPrincipal User principal, @PathVariable Long todoId) {
        ResponseTodoDto response = todoReadService.getTodoById(principal.getUsername(), todoId);

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

    @PutMapping("/todos/{todoId}")
    public ResponseEntity<?> updateTodo(@AuthenticationPrincipal User principal,
                                        @PathVariable Long todoId,
                                        @Valid @RequestBody RequestUpdateTodoDto requestUpdateTodoDto
    ) {
        todoWriteService.updateTodo(principal.getUsername(), todoId, requestUpdateTodoDto);

        return ResponseEntity.ok().body(
                ApiResponse.success()
        );
    }

    @PatchMapping("/todos/{id}")
    public ResponseEntity<?> deleteTodo(@AuthenticationPrincipal User principal, @PathVariable Long id) {
        todoWriteService.deleteTodo(principal.getUsername(), id);

        return ResponseEntity.ok().body(
                ApiResponse.success()
        );
    }

    @GetMapping("/todos/achievement-rate")
    public ResponseEntity<?> getTodoAchievementRate(@AuthenticationPrincipal User principal, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate currentDate) {
        int achievementRate = todoReadService.getTodoAchievementRate(principal.getUsername(), currentDate);

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

    @PostMapping("/todos/remember")
    public ResponseEntity<?> createTodoRemember(@AuthenticationPrincipal User principal,
                                                @Valid @RequestBody RequestCreateTodoRememberDto request
    ) {
        todoWriteService.createTodoRemember(principal.getUsername(), request.getTodoId());

        return ResponseEntity.ok().body(
                ApiResponse.success()
        );
    }

    @PatchMapping("/todos/{todoId}/completed")
    public ResponseEntity<?> changeTodoStatus(@AuthenticationPrincipal User principal,
                                              @PathVariable Long todoId) {
        todoWriteService.changeTodoStatus(principal.getUsername(), todoId);

        return ResponseEntity.ok().body(
                ApiResponse.success()
        );
    }

    @PatchMapping("/todos/{todoId}/pinned")
    public ResponseEntity<?> changeTodoPinned(@AuthenticationPrincipal User principal,
                                              @PathVariable Long todoId,
                                              @RequestParam boolean isPinned) {
        todoWriteService.changeTodoPinned(principal.getUsername(), todoId, isPinned);

        return ResponseEntity.ok().body(
                ApiResponse.success()
        );
    }

    @PostMapping("/todos/{todoId}/tomorrow")
    public ResponseEntity<?> copyTodoToNextDay(@AuthenticationPrincipal User principal,
                                               @PathVariable Long todoId) {
        todoWriteService.copyTodoToNextDay(principal.getUsername(), todoId, LocalDate.now());

        return ResponseEntity.ok().body(
                ApiResponse.success()
        );
    }

    @PostMapping("/todos/{todoId}/emoji/{emojiId}")
    public ResponseEntity<?> createTodoEmoji(@AuthenticationPrincipal User principal,
                                             @PathVariable Long todoId,
                                             @PathVariable Long emojiId
    ) {
        todoWriteService.createTodoEmoji(principal.getUsername(), todoId, emojiId);

        return ResponseEntity.ok().body(
                ApiResponse.success()
        );
    }

}
