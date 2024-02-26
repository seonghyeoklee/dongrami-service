package com.dongrami.todo.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TodoEmojiEntities {

    @OneToMany(mappedBy = "todoEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TodoEmojiEntity> todoEmojiEntities = new ArrayList<>();

    public List<TodoEmojiEntity> getTodoEmojiEntities() {
        return new ArrayList<>(todoEmojiEntities);
    }

}
