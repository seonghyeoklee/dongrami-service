package com.dongrami.todo.domain;

import com.dongrami.exception.BaseException;
import com.dongrami.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TodoEmojiEntities {

    @OneToMany(mappedBy = "todoEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TodoEmojiEntity> todoEmojiEntities = new HashSet<>();

    public void add(TodoEmojiEntity todoEmojiEntity) {
        boolean isAdded = todoEmojiEntities.add(todoEmojiEntity);

        if (!isAdded) {
            throw new BaseException(ErrorCode.HANDLE_ALREADY_EMOJI);
        }
    }

    public Set<TodoEmojiEntity> getTodoEmojiEntities() {
        return new HashSet<>(todoEmojiEntities);
    }

}
