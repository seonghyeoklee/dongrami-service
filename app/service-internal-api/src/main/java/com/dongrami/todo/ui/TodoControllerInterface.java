package com.dongrami.todo.ui;

import com.dongrami.todo.domain.TodoStatus;
import com.dongrami.todo.dto.request.RequestCreateTodoDto;
import com.dongrami.todo.dto.request.RequestCreateTodoRememberDto;
import com.dongrami.todo.dto.request.RequestUpdateTodoDto;
import com.dongrami.todo.dto.response.ResponseTodoDto;
import com.dongrami.todo.repository.support.TodoSearchDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Tag(name = "Todo API", description = "Todo API")
public interface TodoControllerInterface {

    @Operation(
            summary = "Todo 목록 조회 (페이징)",
            parameters = {
                    @Parameter(name = "page", description = "페이징", required = true),
                    @Parameter(name = "size", description = "사이즈", required = true),
                    @Parameter(name = "sort", description = "정렬")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공",
                            content = @Content(schema = @Schema(implementation = ResponseTodoDto.class))),
            }
    )
    ResponseEntity<?> getTodoPage(@Parameter(hidden = true) Pageable pageable, @ParameterObject TodoSearchDto todoSearchDto);

    @Operation(
            summary = "Todo 조회",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공",
                            content = @Content(schema = @Schema(implementation = ResponseTodoDto.class))),
            }
    )
    ResponseEntity<?> getTodoById(@PathVariable Long id);

    @Operation(
            summary = "Todo 생성",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공",
                            content = @Content(schema = @Schema(implementation = ResponseTodoDto.class))),
            }
    )
    ResponseEntity<?> createTodo(@Parameter(hidden = true) User principal, @ParameterObject @Valid @RequestBody RequestCreateTodoDto requestCreateTodoDto);

    @Operation(
            summary = "Todo 수정",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공"),
            }
    )
    ResponseEntity<?> updateTodo(@Parameter(hidden = true) User principal, @PathVariable Long id, @ParameterObject @Valid @RequestBody RequestUpdateTodoDto requestUpdateTodoDto);

    @Operation(
            summary = "Todo 삭제",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공"),
            }
    )
    ResponseEntity<?> deleteTodo(@Parameter(hidden = true) User principal, @PathVariable Long id);

    @Operation(
            summary = "Todo 달성률 조회",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공"),
            }
    )
    ResponseEntity<?> getTodoAchievementRate(@Parameter(hidden = true) User principal);

    @Operation(
            summary = "저장된 Todo 조회",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공"),
            }
    )
    ResponseEntity<?> getTodoRemember(@Parameter(hidden = true) User principal);

    @Operation(
            summary = "Todo 저장하기",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공"),
            }
    )
    ResponseEntity<?> createTodoRemember(@Parameter(hidden = true) User principal, @ParameterObject @Valid @RequestBody RequestCreateTodoRememberDto request);

    @Operation(
            summary = "Todo 상태 변경하기",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공"),
            }
    )
    ResponseEntity<?> changeTodoStatus(@Parameter(hidden = true) User principal, @PathVariable Long id, @RequestParam TodoStatus todoStatus);
}
