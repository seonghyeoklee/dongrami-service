package com.dongrami.tag.application;

import com.dongrami.tag.dto.TagDto;
import com.dongrami.tag.dto.request.RequestCreateTagDto;
import com.dongrami.tag.entity.TagEntity;
import com.dongrami.tag.repository.TagRepository;
import com.dongrami.user.application.UserService;
import com.dongrami.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TagService {
    private final UserService userService;
    private final TagRepository tagRepository;

    public List<TagDto> getTags(String username) {
        UserEntity userEntity = userService.getUserByUserUniqueId(username);

        List<TagEntity> tagEntities = tagRepository.findAllByUserEntity(userEntity);

        return tagEntities.stream()
                .map(TagDto::from)
                .toList();
    }

    public void createTag(String username, RequestCreateTagDto request) {
        UserEntity userEntity = userService.getUserByUserUniqueId(username);

        if (tagRepository.existsByTagNameAndUserEntity(request.getName(), userEntity)) {
            return;
        }

        TagEntity tagEntity = TagEntity.builder()
                .tagName(request.getName())
                .userEntity(userEntity)
                .build();

        tagRepository.save(tagEntity);
    }

    public void deleteTag(String username, Long tagId) {
        UserEntity userEntity = userService.getUserByUserUniqueId(username);

        tagRepository.findByIdAndUserEntity(tagId, userEntity)
                .ifPresent(tagRepository::delete);
    }

}
