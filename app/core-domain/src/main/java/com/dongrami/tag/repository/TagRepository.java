package com.dongrami.tag.repository;

import com.dongrami.tag.entity.TagEntity;
import com.dongrami.user.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<TagEntity, Long> {

    boolean existsByTagNameAndUserEntity(String tagName, UserEntity userEntity);

    Optional<TagEntity> findByIdAndUserEntity(Long tagId, UserEntity userEntity);

    List<TagEntity> findAllByUserEntity(UserEntity userEntity);

}
