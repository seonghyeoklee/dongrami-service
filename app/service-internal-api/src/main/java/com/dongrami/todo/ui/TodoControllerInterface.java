package com.dongrami.todo.ui;

import com.dongrami.todo.dto.request.RequestCreateTodoDto;
import com.dongrami.todo.dto.request.RequestUpdateTodoDto;
import com.dongrami.todo.dto.response.ResponseTodoDto;
import com.dongrami.todo.repository.support.TodoSearchDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Tag(name = "Todo API", description = "Todo API")
public interface TodoControllerInterface {

    @Operation(
            summary = "Todo 목록 조회 (페이징)",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공",
                            content = @Content(schema = @Schema(implementation = ResponseTodoDto.class))),
            }
    )
    ResponseEntity<?> getTodoPage(@PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable, TodoSearchDto todoSearchDto);

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
    ResponseEntity<?> createTodo(@AuthenticationPrincipal User principal, @Valid @RequestBody RequestCreateTodoDto requestCreateTodoDto);

    @Operation(
            summary = "Todo 수정",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공"),
            }
    )
    ResponseEntity<?> updateTodo(@PathVariable Long id, @Valid @RequestBody RequestUpdateTodoDto requestUpdateTodoDto);

    @Operation(
            summary = "Todo 삭제",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공"),
            }
    )
    ResponseEntity<?> deleteTodo(@PathVariable Long id);

}
