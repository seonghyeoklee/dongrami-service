package com.dongrami.user.ui;

import com.dongrami.common.ApiResponse;
import com.dongrami.user.application.UserGroupService;
import com.dongrami.user.dto.request.RequestCreateUserGroupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserGroupController {
    private final UserGroupService userGroupService;

    @PostMapping("/user-groups")
    public ResponseEntity<?> createUserGroup(@AuthenticationPrincipal User principal,
                                             @Valid @RequestBody RequestCreateUserGroupDto request
    ) {
        userGroupService.createUserGroup(principal.getUsername(), request);

        return ResponseEntity.ok(
                ApiResponse.success()
        );
    }

}
