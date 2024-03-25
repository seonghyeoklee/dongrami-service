package com.dongrami.tag.repository.support;

import com.dongrami.tag.entity.TagEntity;
import com.dongrami.user.domain.UserEntity;

import java.util.List;

public interface TagRepositorySupport {

    List<TagEntity> findByTagNameAndUserEntity(String keyword, UserEntity userEntity);

}
