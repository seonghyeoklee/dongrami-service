package com.dongrami.tag.ui;

import com.dongrami.common.ApiResponse;
import com.dongrami.tag.application.TagService;
import com.dongrami.tag.dto.TagDto;
import com.dongrami.tag.dto.request.RequestCreateTagDto;
import com.dongrami.tag.dto.response.ResponseTagDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TagController implements TagControllerInterface {
    private final TagService tagService;

    @GetMapping("/tags")
    public ResponseEntity<?> getTags(@AuthenticationPrincipal User principal) {
        List<TagDto> tags = tagService.getTags(principal.getUsername());

        return ResponseEntity.ok().body(
                ApiResponse.success(
                        tags.stream()
                                .map(tagDto -> ResponseTagDto.builder()
                                        .id(tagDto.getId())
                                        .tagName(tagDto.getTagName())
                                        .user(tagDto.getUser())
                                        .build()
                                )
                                .toList()
                )
        );
    }

    @PostMapping("/tags")
    public ResponseEntity<?> createTag(@AuthenticationPrincipal User principal,
                                       @Valid @RequestBody RequestCreateTagDto request) {
        tagService.createTag(principal.getUsername(), request);

        return ResponseEntity.ok().body(
                ApiResponse.success()
        );
    }

    @DeleteMapping("/tags/{tagId}")
    public ResponseEntity<?> deleteTag(@AuthenticationPrincipal User principal,
                                       @PathVariable Long tagId) {
        tagService.deleteTag(principal.getUsername(), tagId);

        return ResponseEntity.ok().body(
                ApiResponse.success()
        );
    }

}
