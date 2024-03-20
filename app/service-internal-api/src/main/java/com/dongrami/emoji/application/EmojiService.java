package com.dongrami.emoji.application;

import com.dongrami.emoji.domain.EmojiEntity;
import com.dongrami.emoji.dto.EmojiDto;
import com.dongrami.emoji.dto.request.RequestCreateEmojiDto;
import com.dongrami.emoji.dto.request.RequestUpdateEmojiDto;
import com.dongrami.emoji.repository.EmojiRepository;
import com.dongrami.exception.BaseException;
import com.dongrami.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EmojiService {
    private final EmojiRepository emojiRepository;

    public EmojiDto getEmojis(Long emojiId) {
        EmojiEntity emojiEntity = emojiRepository.findById(emojiId)
                .orElseThrow(() -> new BaseException(ErrorCode.NO_CONTENT));

        return EmojiDto.from(emojiEntity);
    }

    public void createEmoji(RequestCreateEmojiDto request) {
        emojiRepository.findByName(request.getName())
                .ifPresent(emojiEntity -> {
                    throw new BaseException(ErrorCode.EMOJI_NAME_ALREADY_EXIST);
                });

        EmojiEntity emojiEntity = EmojiEntity.builder()
                .name(request.getName())
                .icon(request.getIcon())
                .isDeleted(false)
                .build();

        emojiRepository.save(emojiEntity);
    }

    public void updateEmoji(Long emojiId, RequestUpdateEmojiDto request) {
        EmojiEntity emojiEntity = emojiRepository.findById(emojiId)
                .orElseThrow(() -> new BaseException(ErrorCode.NO_CONTENT));

        emojiEntity.update(request.getName(), request.getIcon(), request.getIsDeleted());
    }

}
