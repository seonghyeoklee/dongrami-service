package com.dongrami.diary.ui;

import com.dongrami.common.ApiResponse;
import com.dongrami.diary.application.DiaryService;
import com.dongrami.diary.dto.DiaryDto;
import com.dongrami.diary.dto.request.RequestCreateDiaryDto;
import com.dongrami.diary.dto.request.RequestUpdateDiaryDto;
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

    @PostMapping("/diaries")
    public ResponseEntity<?> createDiary(
            @AuthenticationPrincipal User principal,
            @Valid @RequestBody RequestCreateDiaryDto request
    ) {
        diaryService.createDiary(principal.getUsername(), request);

        return ResponseEntity.ok().body(
                ApiResponse.success(null)
        );
    }

    @GetMapping("/diaries/{id}")
    public ResponseEntity<?> getDiaryById(@PathVariable Long id) {
        DiaryDto response = diaryService.getDiaryById(id);

        return ResponseEntity.ok().body(
                ApiResponse.success(response)
        );
    }

    @PutMapping("/diaries/{id}")
    public ResponseEntity<?> updateDiary(
            @PathVariable Long id,
            @Valid @RequestBody RequestUpdateDiaryDto request
    ) {
        diaryService.updateDiary(id, request);

        return ResponseEntity.ok().body(
                ApiResponse.success(null)
        );
    }

    @DeleteMapping("/diaries/{id}")
    public ResponseEntity<?> deleteDiary(@PathVariable Long id) {
        diaryService.deleteDiary(id);

        return ResponseEntity.ok().body(
                ApiResponse.success(null)
        );
    }

}
