package com.dongrami.user.ui;

import com.dongrami.common.ApiResponse;
import com.dongrami.user.application.UserGroupService;
import com.dongrami.user.dto.request.RequestCreateUserGroupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/user-groups")
    public ResponseEntity<?> getUserGroup(@AuthenticationPrincipal User principal) {
        return ResponseEntity.ok(
                ApiResponse.success(
                        userGroupService.getUserGroup(principal.getUsername())
                )
        );
    }

    @DeleteMapping("/user-groups")
    public ResponseEntity<?> deleteUserGroup(@AuthenticationPrincipal User principal) {
        userGroupService.deleteUserGroup(principal.getUsername());
        return ResponseEntity.ok(
                ApiResponse.success()
        );
    }

}
