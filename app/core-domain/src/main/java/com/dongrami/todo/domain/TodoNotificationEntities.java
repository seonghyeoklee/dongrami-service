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
public class TodoNotificationEntities {

    @OneToMany(mappedBy = "todoEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TodoNotificationEntity> todoNotificationEntities = new ArrayList<>();

    public void add(TodoNotificationEntity todoNotificationEntity) {
        this.todoNotificationEntities.add(todoNotificationEntity);
    }

    public List<TodoNotificationEntity> getTodoNotificationEntities() {
        return new ArrayList<>(todoNotificationEntities);
    }

}
