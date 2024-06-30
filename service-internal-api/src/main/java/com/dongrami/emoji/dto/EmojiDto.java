package com.dongrami.emoji.dto;

import com.dongrami.emoji.domain.EmojiEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmojiDto {
    private Long id;
    private String icon;
    private String name;

    @JsonProperty("isDeleted")
    private boolean isDeleted;

    public static EmojiDto from(EmojiEntity emojiEntity) {
        return EmojiDto.builder()
                .id(emojiEntity.getId())
                .icon(emojiEntity.getIcon())
                .name(emojiEntity.getName())
                .isDeleted(emojiEntity.isDeleted())
                .build();
    }
}
