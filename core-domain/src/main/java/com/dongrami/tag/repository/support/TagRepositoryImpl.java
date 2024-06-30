package com.dongrami.tag.repository.support;

import com.dongrami.tag.entity.TagEntity;
import com.dongrami.user.domain.UserEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.dongrami.tag.entity.QTagEntity.tagEntity;

@RequiredArgsConstructor
public class TagRepositoryImpl implements TagRepositorySupport {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<TagEntity> findByTagNameAndUserEntity(String tagName, UserEntity userEntity) {
        return queryFactory
                .selectFrom(tagEntity)
                .where(
                        tagEntity.tagName.contains(tagName)
                                .and(tagEntity.userEntity.eq(userEntity))
                )
                .fetch();
    }

}
