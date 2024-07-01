package com.dongrami.todo.ui;

import com.dongrami.common.ApiResponse;
import com.dongrami.todo.application.TodoReadService;
import com.dongrami.todo.application.TodoWriteService;
import com.dongrami.todo.dto.request.RequestCreateTodo;
import com.dongrami.todo.dto.request.RequestCreateTodoRemember;
import com.dongrami.todo.dto.request.RequestUpdateTodo;
import com.dongrami.todo.dto.response.ResponseTodoAchievementRate;
import com.dongrami.todo.dto.response.ResponseTodoDetail;
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

    /**
     * 할일 목록 조회 (페이징)
     */
    @GetMapping("/todos")
    public ResponseEntity<?> getTodoPage(@AuthenticationPrincipal User principal,
                                         Pageable pageable,
                                         @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate currentDate) {
        Page<ResponseTodoDetail> responses = todoReadService.getTodoPageByCurrentDate(principal.getUsername(), pageable, currentDate);

        return ResponseEntity.ok().body(
                ApiResponse.success(responses)
        );
    }

    @GetMapping("/todos/{todoId}")
    public ResponseEntity<?> getTodoById(@AuthenticationPrincipal User principal, @PathVariable Long todoId) {
        ResponseTodoDetail response = todoReadService.getTodoById(principal.getUsername(), todoId);

        return ResponseEntity.ok().body(
                ApiResponse.success(response)
        );
    }

    @PostMapping("/todos")
    public ResponseEntity<?> createTodo(@AuthenticationPrincipal User principal,
                                        @Valid @RequestBody RequestCreateTodo request) {
        ResponseTodoDetail response = todoWriteService.createTodo(principal.getUsername(), request);

        return ResponseEntity.ok().body(
                ApiResponse.success(response)
        );
    }

    @PutMapping("/todos/{todoId}")
    public ResponseEntity<?> updateTodo(@AuthenticationPrincipal User principal,
                                        @PathVariable Long todoId,
                                        @Valid @RequestBody RequestUpdateTodo request) {
        todoWriteService.updateTodo(principal.getUsername(), todoId, request);

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
    public ResponseEntity<?> getTodoAchievementRate(@AuthenticationPrincipal User principal,
                                                    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate currentDate) {
        double achievementRate = todoReadService.getTodoAchievementRate(principal.getUsername(), currentDate);

        return ResponseEntity.ok().body(
                ApiResponse.success(ResponseTodoAchievementRate.of(achievementRate))
        );
    }

    @GetMapping("/todos/remember")
    public ResponseEntity<?> getTodoRemember(@AuthenticationPrincipal User principal) {
        List<ResponseTodoDetail> responses = todoReadService.getTodoRemember(principal.getUsername());

        return ResponseEntity.ok().body(
                ApiResponse.success(responses)
        );
    }

    @PostMapping("/todos/remember")
    public ResponseEntity<?> createTodoRemember(@AuthenticationPrincipal User principal,
                                                @Valid @RequestBody RequestCreateTodoRemember request
    ) {
        todoWriteService.createTodoRemember(principal.getUsername(), request.todoId());

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
