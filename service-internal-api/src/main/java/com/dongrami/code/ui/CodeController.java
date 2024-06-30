package com.dongrami.code.ui;

import com.dongrami.code.application.CodeService;
import com.dongrami.code.dto.request.RequestCreateCodeDto;
import com.dongrami.code.dto.request.RequestUpdateCodeDto;
import com.dongrami.code.dto.response.ResponseCodeDto;
import com.dongrami.code.repository.support.CodeGroupSearchDto;
import com.dongrami.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CodeController {
    private final CodeService codeService;

    @GetMapping("/codes")
    public ResponseEntity<?> getCodePage(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
            CodeGroupSearchDto codeGroupSearchDto
    ) {
        Page<ResponseCodeDto> responses = codeService.getCodePageBySearch(pageable, codeGroupSearchDto);

        return ResponseEntity.ok().body(
                ApiResponse.success(responses)
        );
    }

    @GetMapping("/codes/{id}")
    public ResponseEntity<?> getCodeById(@PathVariable Long id) {
        ResponseCodeDto response = codeService.getCodeById(id);

        return ResponseEntity.ok().body(
                ApiResponse.success(response)
        );
    }

    @PostMapping("/codes")
    public ResponseEntity<?> createCode(@Valid @RequestBody RequestCreateCodeDto requestCreateCodeDto) {
        codeService.createCode(requestCreateCodeDto);

        return ResponseEntity.ok().body(
                ApiResponse.success()
        );
    }

    @PutMapping("/codes/{id}")
    public ResponseEntity<?> updateCode(@PathVariable Long id, @Valid @RequestBody RequestUpdateCodeDto requestUpdateCodeDto) {
        codeService.updateCode(id, requestUpdateCodeDto);

        return ResponseEntity.ok().body(
                ApiResponse.success()
        );
    }

    @DeleteMapping("/codes/{id}")
    public ResponseEntity<?> deleteCode(@PathVariable Long id) {
        codeService.deleteCode(id);

        return ResponseEntity.ok().body(
                ApiResponse.success()
        );
    }

}
