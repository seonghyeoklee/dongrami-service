package com.dongrami.user.ui;

import com.dongrami.common.ApiResponse;
import com.dongrami.user.application.UserService;
import com.dongrami.user.domain.UserEntity;
import com.dongrami.user.dto.request.RequestUpdateProfileInfoDto;
import com.dongrami.user.dto.response.ResponseProfileInfoDto;
import com.dongrami.user.dto.response.ResponseUserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;

    /**
     * 사용자 정보 조회
     */
    @GetMapping("/users")
    public ResponseEntity<?> getUser(@AuthenticationPrincipal User principal) {
        UserEntity user = userService.getUser(principal.getUsername());

        return ResponseEntity.ok(
                ApiResponse.success(ResponseUserInfoDto.from(user))
        );
    }

    /**
     * 프로필 정보 조회
     */
    @GetMapping("/users/profile-info")
    public ResponseEntity<?> getProfileInfo(@AuthenticationPrincipal User principal) {
        UserEntity user = userService.getUserByUserUniqueId(principal.getUsername());
        return ResponseEntity.ok(
                ApiResponse.success(ResponseProfileInfoDto.from(user))
        );
    }

    /**
     * 프로필 정보 수정 (nickname, location)
     */
    @PutMapping("/users/profile-info")
    public ResponseEntity<?> updateProfileInfo(@AuthenticationPrincipal User principal,
                                               @Valid @RequestBody RequestUpdateProfileInfoDto request) {
        userService.updateProfileInfo(principal.getUsername(), request);

        return ResponseEntity.ok(
                ApiResponse.success()
        );
    }

}
