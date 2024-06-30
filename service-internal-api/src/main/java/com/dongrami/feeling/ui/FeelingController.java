package com.dongrami.feeling.ui;

import com.dongrami.common.ApiResponse;
import com.dongrami.feeling.application.FeelingService;
import com.dongrami.feeling.dto.FeelingDto;
import com.dongrami.feeling.dto.request.RequestCreateFeelingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class FeelingController {
    private final FeelingService feelingService;

    @GetMapping("/feelings")
    public ResponseEntity<?> getFeelings() {
        List<FeelingDto> responses = feelingService.getFeelings(false);

        return ResponseEntity.ok().body(
                ApiResponse.success(responses)
        );
    }

    @PostMapping("/feelings")
    public ResponseEntity<?> createFeeling(@Valid @RequestBody RequestCreateFeelingDto request) {
        feelingService.createFeeling(request);

        return ResponseEntity.ok().body(
                ApiResponse.success()
        );
    }

}
