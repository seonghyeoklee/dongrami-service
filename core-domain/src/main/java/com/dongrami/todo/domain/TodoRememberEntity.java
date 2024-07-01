package com.dongrami.todo.domain;

import com.dongrami.common.BaseTimeEntity;
import com.dongrami.user.domain.UserEntity;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Table(name = "todo_remember")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TodoRememberEntity extends BaseTimeEntity {

    @Comment("PK")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("할일")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id", foreignKey = @ForeignKey(name = "fk_todo_remember_todo"))
    private TodoEntity todoEntity;

    @Comment("기억하기 요청한 사용자")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_todo_remember_user"))
    private UserEntity userEntity;
}
