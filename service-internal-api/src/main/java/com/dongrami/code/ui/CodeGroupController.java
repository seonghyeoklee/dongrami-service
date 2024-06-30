package com.dongrami.code.ui;

import com.dongrami.code.application.CodeGroupService;
import com.dongrami.code.dto.request.RequestCreateCodeGroupDto;
import com.dongrami.code.dto.request.RequestUpdateCodeGroupDto;
import com.dongrami.code.dto.response.ResponseCodeGroupDto;
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
public class CodeGroupController {
    private final CodeGroupService codeGroupService;

    @GetMapping("/code-groups")
    public ResponseEntity<?> getCodeGroupPage(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
            CodeGroupSearchDto codeGroupSearchDto
    ) {
        Page<ResponseCodeGroupDto> responses = codeGroupService.getCodeGroupPageBySearch(pageable, codeGroupSearchDto);

        return ResponseEntity.ok().body(
                ApiResponse.success(responses)
        );
    }

    @GetMapping("/code-groups/{id}")
    public ResponseEntity<?> getCodeGroupById(@PathVariable Long id) {
        ResponseCodeGroupDto response = codeGroupService.getCodeGroupById(id);

        return ResponseEntity.ok().body(
                ApiResponse.success(response)
        );
    }

    @PostMapping("/code-groups")
    public ResponseEntity<?> createCodeGroup(@Valid @RequestBody RequestCreateCodeGroupDto requestCreateCodeGroupDto) {
        codeGroupService.createCodeGroup(requestCreateCodeGroupDto);

        return ResponseEntity.ok().body(
                ApiResponse.success()
        );
    }

    @PutMapping("/code-groups/{id}")
    public ResponseEntity<?> updateCodeGroup(@PathVariable Long id, @Valid @RequestBody RequestUpdateCodeGroupDto requestUpdateCodeGroupDto) {
        codeGroupService.updateCodeGroup(id, requestUpdateCodeGroupDto);

        return ResponseEntity.ok().body(
                ApiResponse.success()
        );
    }

    @DeleteMapping("/code-groups/{id}")
    public ResponseEntity<?> deleteCodeGroup(@PathVariable Long id) {
        codeGroupService.deleteCodeGroup(id);

        return ResponseEntity.ok().body(
                ApiResponse.success()
        );
    }

}
