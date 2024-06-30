package com.dongrami.emoji.ui;

import com.dongrami.common.ApiResponse;
import com.dongrami.emoji.application.EmojiService;
import com.dongrami.emoji.dto.EmojiDto;
import com.dongrami.emoji.dto.request.RequestCreateEmojiDto;
import com.dongrami.emoji.dto.request.RequestUpdateEmojiDto;
import com.dongrami.emoji.dto.response.ResponseEmojiDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class EmojiController implements EmojiControllerInterface {
    private final EmojiService emojiService;

    @GetMapping("/emojis/{emojiId}")
    public ResponseEntity<?> getEmojis(@PathVariable Long emojiId) {
        EmojiDto emojiDto = emojiService.getEmojis(emojiId);

        return ResponseEntity.ok().body(
                ApiResponse.success(
                        ResponseEmojiDto.builder()
                                .id(emojiDto.getId())
                                .icon(emojiDto.getIcon())
                                .name(emojiDto.getName())
                                .isDeleted(emojiDto.isDeleted())
                                .build()
                )
        );
    }

    @PostMapping("/emojis")
    public ResponseEntity<?> createEmojis(@Valid @RequestBody RequestCreateEmojiDto request) {
        emojiService.createEmoji(request);

        return ResponseEntity.ok().body(
                ApiResponse.success()
        );
    }

    @PutMapping("/emojis/{emojiId}")
    public ResponseEntity<?> updateEmojis(@PathVariable Long emojiId, @Valid @RequestBody RequestUpdateEmojiDto request) {
        emojiService.updateEmoji(emojiId, request);

        return ResponseEntity.ok().body(
                ApiResponse.success()
        );
    }

}
