package com.dongrami.diary.ui;

import com.dongrami.todo.dto.response.ResponseTodoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDate;

@Tag(name = "Diary API", description = "일기를 관리하는 API")
public interface DiaryControllerInterface {

    @Operation(
            summary = "일기 목록 조회 (페이징)",
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
    ResponseEntity<?> getDiaryPage(@Parameter(hidden = true) User principal, @Parameter(hidden = true) Pageable pageable, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate currentDate);

}
