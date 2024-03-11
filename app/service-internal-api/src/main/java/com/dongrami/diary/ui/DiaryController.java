package com.dongrami.diary.ui;

import com.dongrami.common.ApiResponse;
import com.dongrami.diary.application.DiaryService;
import com.dongrami.diary.dto.DiaryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class DiaryController {
    private final DiaryService diaryService;

    @GetMapping("/diaries")
    public ResponseEntity<?> getDiaryPage(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<DiaryDto> responses = diaryService.getDiaryPage(pageable);

        return ResponseEntity.ok().body(
                ApiResponse.success(responses)
        );
    }

}
