package com.dongrami.user.ui;

import com.dongrami.common.ApiResponse;
import com.dongrami.user.application.UserService;
import com.dongrami.user.dto.request.RequestInviteCode;
import com.dongrami.user.dto.response.ResponseInviteCode;
import com.dongrami.user.dto.response.ResponsePairUserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserPairController implements UserPairControllerInterface {
    private final UserService userService;

    /**
     * 짝꿍 초대코드 조회
     */
    @GetMapping("/users/pair/invite")
    public ResponseEntity<?> getInviteCode(@AuthenticationPrincipal User principal) {
        String inviteCode = userService.getInviteCode(principal.getUsername());

        return ResponseEntity.ok(
                ApiResponse.success(new ResponseInviteCode(inviteCode))
        );
    }

    /**
     * 짝꿍 초대코드 입력
     */
    @PostMapping("/users/pair/invite")
    public ResponseEntity<?> updateInviteCode(@AuthenticationPrincipal User principal, @Valid @RequestBody RequestInviteCode request) {
        userService.updateInviteCode(principal.getUsername(), request);

        return ResponseEntity.ok(
                ApiResponse.success()
        );
    }

    /**
     * 짝꿍 정보 조회
     */
    @GetMapping("/users/pair")
    public ResponseEntity<?> getPairUserInfo(@AuthenticationPrincipal User principal) {
        ResponsePairUserInfo response = userService.getPairUserInfo(principal.getUsername());

        return ResponseEntity.ok(
                ApiResponse.success(response)
        );
    }

    /**
     * 짝꿍 해제
     */
    @PutMapping("/users/pair")
    public ResponseEntity<?> deletePairUser(@AuthenticationPrincipal User principal) {
        userService.deletePairUser(principal.getUsername());

        return ResponseEntity.ok(
                ApiResponse.success()
        );
    }
}
