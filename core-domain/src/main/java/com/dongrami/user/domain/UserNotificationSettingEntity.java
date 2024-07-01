package com.dongrami.user.domain;

import com.dongrami.common.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Table(name = "user_notification_setting")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserNotificationSettingEntity extends BaseTimeEntity {

    @Comment("PK")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Comment("알림 타입")
    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    @Comment("알림 사용 여부")
    @Column
    private boolean isEnabled;

    public void updateNotificationSetting() {
        this.isEnabled = !this.isEnabled;
    }
}
