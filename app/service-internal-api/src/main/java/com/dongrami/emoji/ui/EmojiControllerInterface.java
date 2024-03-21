package com.dongrami.emoji.ui;

import com.dongrami.emoji.dto.request.RequestCreateEmojiDto;
import com.dongrami.emoji.dto.request.RequestUpdateEmojiDto;
import com.dongrami.emoji.dto.response.ResponseEmojiDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Tag(name = "Emoji API", description = "이모지를 관리하는 API")
public interface EmojiControllerInterface {

    @Operation(
            summary = "이모지 조회",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공",
                            content = @Content(schema = @Schema(implementation = ResponseEmojiDto.class))),
            }
    )
    ResponseEntity<?> getEmojis(@PathVariable Long emojiId);

    @Operation(
            summary = "이모지 생성",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공"),
            }
    )
    ResponseEntity<?> createEmojis(@Valid @RequestBody RequestCreateEmojiDto request);

    @Operation(
            summary = "이모지 생성",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공"),
            }
    )
    ResponseEntity<?> updateEmojis(@PathVariable Long emojiId, @Valid @RequestBody RequestUpdateEmojiDto request);

}
