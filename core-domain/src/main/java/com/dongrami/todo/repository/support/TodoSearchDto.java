package com.dongrami.todo.repository.support;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class TodoSearchDto {

    @Schema(description = "조회 날짜", example = "2024-02-05")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate currentDate;

}
