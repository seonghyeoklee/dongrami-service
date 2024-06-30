package com.dongrami.cache;

import com.dongrami.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CacheController {
    private final CacheService cacheService;

    @GetMapping("/caches")
    public ResponseEntity<?> getCalendar() {

        return ResponseEntity.ok().body(
                ApiResponse.success(cacheService.getCaches())
        );
    }

}
