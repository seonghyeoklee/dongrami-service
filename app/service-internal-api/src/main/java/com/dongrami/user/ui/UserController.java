package com.dongrami.user.ui;

import com.dongrami.common.ApiResponse;
import com.dongrami.user.application.UserService;
import com.dongrami.user.domain.UserEntity;
import com.dongrami.user.dto.response.ResponseUserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<?> getUser(@AuthenticationPrincipal User principal) {

        UserEntity user = userService.getUser(principal.getUsername());

        return ResponseEntity.ok(
                ApiResponse.success(ResponseUserInfoDto.from(user))
        );
    }

}
