package com.dongrami.user.ui;

import com.dongrami.common.ApiResponse;
import com.dongrami.user.application.UserService;
import com.dongrami.user.domain.UserEntity;
import com.dongrami.user.dto.request.RequestDeactivation;
import com.dongrami.user.dto.request.RequestUpdateNotification;
import com.dongrami.user.dto.request.RequestUpdateProfileInfo;
import com.dongrami.user.dto.response.ResponseCountTodoAndDiary;
import com.dongrami.user.dto.response.ResponseProfileInfo;
import com.dongrami.user.dto.response.ResponseUserInfo;
import com.dongrami.user.dto.response.ResponseUserNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController implements UserControllerInterface {
    private final UserService userService;

    /**
     * 사용자 정보 조회
     */
    @GetMapping("/users")
    public ResponseEntity<?> getUser(@AuthenticationPrincipal User principal) {
        UserEntity user = userService.getUser(principal.getUsername());

        return ResponseEntity.ok(
                ApiResponse.success(ResponseUserInfo.of(user))
        );
    }

    /**
     * 프로필 정보 조회
     */
    @GetMapping("/users/profile-info")
    public ResponseEntity<?> getProfileInfo(@AuthenticationPrincipal User principal) {
        UserEntity user = userService.getUserByUserUniqueId(principal.getUsername());
        return ResponseEntity.ok(
                ApiResponse.success(ResponseProfileInfo.from(user))
        );
    }

    /**
     * 프로필 정보 수정 (nickname, location)
     */
    @PutMapping("/users/profile-info")
    public ResponseEntity<?> updateProfileInfo(@AuthenticationPrincipal User principal,
                                               @Valid @RequestBody RequestUpdateProfileInfo request) {
        userService.updateProfileInfo(principal.getUsername(), request);

        return ResponseEntity.ok(ApiResponse.success());
    }

    /**
     * 월경 기능 활성화
     */
    @PutMapping("/users/menstrual")
    public ResponseEntity<?> updateMenstrual(@AuthenticationPrincipal User principal) {
        userService.updateMenstrual(principal.getUsername());

        return ResponseEntity.ok(ApiResponse.success());
    }

    /**
     * 회원 탈퇴
     */
    @PostMapping("/users/deactivation")
    public ResponseEntity<?> updateDeactivation(@AuthenticationPrincipal User principal, @Valid @RequestBody RequestDeactivation request) {
        userService.updateDeactivation(principal.getUsername(), request);

        return ResponseEntity.ok(ApiResponse.success());
    }

    /**
     * 회원 탈퇴 시 할일, 기록 건수 조회
     */
    @GetMapping("/users/deactivation")
    public ResponseEntity<?> countTodoAndDiary(@AuthenticationPrincipal User principal) {
        ResponseCountTodoAndDiary response = userService.countTodoAndDiary(principal.getUsername());

        return ResponseEntity.ok(ApiResponse.success(response));
    }

    /**
     * 알림 설정 조회
     */
    @GetMapping("/users/notification")
    public ResponseEntity<?> getNotification(@AuthenticationPrincipal User principal) {
        List<ResponseUserNotification> responses = userService.getNotification(principal.getUsername());

        return ResponseEntity.ok(
                ApiResponse.success(responses)
        );
    }

    /**
     * 알림 설정
     */
    @PutMapping("/users/notification")
    public ResponseEntity<?> updateNotification(@AuthenticationPrincipal User principal, @Valid @RequestBody RequestUpdateNotification request) {
        userService.updateNotification(principal.getUsername(), request);

        return ResponseEntity.ok(
                ApiResponse.success()
        );
    }
}
