package com.dongrami.tag.ui;

import com.dongrami.tag.dto.request.RequestCreateTagDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Tag(name = "Tag API", description = "태그를 관리하는 API")
public interface TagControllerInterface {

    @Operation(
            summary = "태그 생성하기",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공"),
            }
    )
    ResponseEntity<?> createTag(@Parameter(hidden = true) User principal, @ParameterObject @Valid @RequestBody RequestCreateTagDto request);

    @Operation(
            summary = "태그 삭제하기",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공"),
            }
    )
    ResponseEntity<?> deleteTag(@Parameter(hidden = true) User principal, @PathVariable Long tagId);

}
