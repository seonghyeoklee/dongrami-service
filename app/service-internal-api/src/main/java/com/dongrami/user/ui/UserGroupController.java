package com.dongrami.user.ui;

import com.dongrami.common.ApiResponse;
import com.dongrami.user.application.UserGroupService;
import com.dongrami.user.dto.request.RequestCreateUserGroupDto;
import com.dongrami.user.dto.request.RequestJoinUserGroupDto;
import com.dongrami.user.dto.request.RequestUpdateUserGroupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserGroupController implements UserGroupControllerInterface {
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

    @PostMapping("/user-groups/join")
    public ResponseEntity<?> joinUserGroup(@AuthenticationPrincipal User principal,
                                           @Valid @RequestBody RequestJoinUserGroupDto request
    ) {
        userGroupService.joinUserGroup(principal.getUsername(), request.getGroupCode());
        return ResponseEntity.ok(
                ApiResponse.success()
        );
    }

    @PostMapping("/user-groups/leave")
    public ResponseEntity<?> leaveUserGroup(@AuthenticationPrincipal User principal) {
        userGroupService.leaveUserGroup(principal.getUsername());
        return ResponseEntity.ok(
                ApiResponse.success()
        );
    }

    @PutMapping("/user-groups")
    public ResponseEntity<?> updateUserGroup(@AuthenticationPrincipal User principal,
                                             @Valid @RequestBody RequestUpdateUserGroupDto request
    ) {
        userGroupService.updateUserGroup(principal.getUsername(), request);
        return ResponseEntity.ok(
                ApiResponse.success()
        );
    }

}
