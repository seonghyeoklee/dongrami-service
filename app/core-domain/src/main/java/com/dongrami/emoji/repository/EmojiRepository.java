package com.dongrami.emoji.repository;

import com.dongrami.emoji.domain.EmojiEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmojiRepository extends JpaRepository<EmojiEntity, Long> {

    Optional<EmojiEntity> findByName(String name);

}
