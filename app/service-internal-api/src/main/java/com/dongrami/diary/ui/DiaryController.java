package com.dongrami.diary.ui;

import com.dongrami.common.ApiResponse;
import com.dongrami.diary.application.DiaryService;
import com.dongrami.diary.dto.DiaryDto;
import com.dongrami.diary.dto.request.RequestCreateDiaryDto;
import com.dongrami.diary.dto.request.RequestUpdateDiaryDto;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class DiaryController implements DiaryControllerInterface {
    private final DiaryService diaryService;

    @GetMapping("/diaries")
    public ResponseEntity<?> getDiaryPage(@AuthenticationPrincipal User principal,
                                          Pageable pageable,
                                          @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate currentDate) {
        Page<DiaryDto> responses = diaryService.getDiaryPage(principal.getUsername(), pageable, currentDate);

        return ResponseEntity.ok().body(
                ApiResponse.success(responses)
        );
    }

    @PostMapping("/diaries")
    public ResponseEntity<?> createDiary(@AuthenticationPrincipal User principal,
                                         @Valid @RequestBody RequestCreateDiaryDto request) {
        diaryService.createDiary(principal.getUsername(), request);

        return ResponseEntity.ok().body(
                ApiResponse.success(null)
        );
    }

    @GetMapping("/diaries/{diaryId}")
    public ResponseEntity<?> getDiaryById(@AuthenticationPrincipal User principal,
                                          @PathVariable Long diaryId) {
        DiaryDto response = diaryService.getDiaryById(principal.getUsername(), diaryId);

        return ResponseEntity.ok().body(
                ApiResponse.success(response)
        );
    }

    @PutMapping("/diaries/{diaryId}")
    public ResponseEntity<?> updateDiary(@PathVariable Long diaryId, @Valid @RequestBody RequestUpdateDiaryDto request) {
        diaryService.updateDiary(diaryId, request);

        return ResponseEntity.ok().body(
                ApiResponse.success(null)
        );
    }

    @DeleteMapping("/diaries/{diaryId}")
    public ResponseEntity<?> deleteDiary(@PathVariable Long diaryId) {
        diaryService.deleteDiary(diaryId);

        return ResponseEntity.ok().body(
                ApiResponse.success(null)
        );
    }

}
