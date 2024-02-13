package com.dongrami.todo.domain;

import com.dongrami.common.BaseTimeEntity;
import com.dongrami.emoji.domain.EmojiEntity;
import com.dongrami.user.domain.UserEntity;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Table(name = "todo_emoji")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TodoEmojiEntity extends BaseTimeEntity {

    @Comment("PK")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("이모지")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emoji_id", foreignKey = @ForeignKey(name = "fk_todo_emoji_emoji"))
    private EmojiEntity emojiEntity;

    @Comment("이모지 누른 사용자")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_todo_emoji_user"))
    private UserEntity userEntity;

    @Comment("할일")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id", foreignKey = @ForeignKey(name = "fk_todo_emoji_todo"))
    private TodoEntity todoEntity;

}
