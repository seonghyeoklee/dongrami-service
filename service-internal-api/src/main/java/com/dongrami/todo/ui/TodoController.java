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

    /**
     * 할일 조회
     */
    @GetMapping("/todos/{todoId}")
    public ResponseEntity<?> getTodoById(@AuthenticationPrincipal User principal, @PathVariable Long todoId) {
        ResponseTodoDetail response = todoReadService.getTodoById(principal.getUsername(), todoId);

        return ResponseEntity.ok().body(
                ApiResponse.success(response)
        );
    }

    /**
     * 할일 생성
     */
    @PostMapping("/todos")
    public ResponseEntity<?> createTodo(@AuthenticationPrincipal User principal,
                                        @Valid @RequestBody RequestCreateTodo request) {
        ResponseTodoDetail response = todoWriteService.createTodo(principal.getUsername(), request);

        return ResponseEntity.ok().body(
                ApiResponse.success(response)
        );
    }

    /**
     * 할일 수정
     */
    @PutMapping("/todos/{todoId}")
    public ResponseEntity<?> updateTodo(@AuthenticationPrincipal User principal,
                                        @PathVariable Long todoId,
                                        @Valid @RequestBody RequestUpdateTodo request) {
        todoWriteService.updateTodo(principal.getUsername(), todoId, request);

        return ResponseEntity.ok().body(
                ApiResponse.success()
        );
    }

    /**
     * 할일 삭제
     */
    @DeleteMapping("/todos/{todoId}")
    public ResponseEntity<?> deleteTodo(@AuthenticationPrincipal User principal, @PathVariable Long todoId) {
        todoWriteService.deleteTodo(principal.getUsername(), todoId);

        return ResponseEntity.ok().body(
                ApiResponse.success()
        );
    }

    /**
     * 할일 달성률 조회
     */
    @GetMapping("/todos/achievement-rate")
    public ResponseEntity<?> getTodoAchievementRate(@AuthenticationPrincipal User principal,
                                                    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate currentDate) {
        int achievementRate = todoReadService.getTodoAchievementRate(principal.getUsername(), currentDate);

        return ResponseEntity.ok().body(
                ApiResponse.success(ResponseTodoAchievementRate.of(achievementRate))
        );
    }

    /**
     * 저장된 할일 조회
     */
    @GetMapping("/todos/remember")
    public ResponseEntity<?> getTodoRemember(@AuthenticationPrincipal User principal) {
        List<ResponseTodoDetail> responses = todoReadService.getTodoRemember(principal.getUsername());

        return ResponseEntity.ok().body(
                ApiResponse.success(responses)
        );
    }

    /**
     * 할일 저장하기
     */
    @PostMapping("/todos/remember")
    public ResponseEntity<?> createTodoRemember(@AuthenticationPrincipal User principal,
                                                @Valid @RequestBody RequestCreateTodoRemember request
    ) {
        todoWriteService.createTodoRemember(principal.getUsername(), request.todoId());

        return ResponseEntity.ok().body(
                ApiResponse.success()
        );
    }

    /**
     * 할일 완료 상태 변경
     */
    @PutMapping("/todos/{todoId}/completed")
    public ResponseEntity<?> changeTodoStatus(@AuthenticationPrincipal User principal,
                                              @PathVariable Long todoId) {
        todoWriteService.changeTodoStatus(principal.getUsername(), todoId);

        return ResponseEntity.ok().body(
                ApiResponse.success()
        );
    }

    /**
     * 할일 고정 상태 변경
     */
    @PutMapping("/todos/{todoId}/pinned")
    public ResponseEntity<?> changeTodoPinned(@AuthenticationPrincipal User principal,
                                              @PathVariable Long todoId,
                                              @RequestParam boolean isPinned) {
        todoWriteService.changeTodoPinned(principal.getUsername(), todoId, isPinned);

        return ResponseEntity.ok().body(
                ApiResponse.success()
        );
    }

    /**
     * 할일 내일로 복사
     */
    @PostMapping("/todos/{todoId}/tomorrow")
    public ResponseEntity<?> copyTodoToNextDay(@AuthenticationPrincipal User principal,
                                               @PathVariable Long todoId) {
        todoWriteService.copyTodoToNextDay(principal.getUsername(), todoId, LocalDate.now());

        return ResponseEntity.ok().body(
                ApiResponse.success()
        );
    }

    /**
     * 할일 이모지 반응 추가
     */
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
