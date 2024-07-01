package com.dongrami.user.ui;

import com.dongrami.user.dto.request.RequestDeactivation;
import com.dongrami.user.dto.request.RequestInviteCode;
import com.dongrami.user.dto.request.RequestUpdateNotification;
import com.dongrami.user.dto.request.RequestUpdateProfileInfo;
import com.dongrami.user.dto.response.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Tag(name = "사용자 API", description = "사용자 관련 API")
public interface UserControllerInterface {

    @Operation(
            summary = "로그인 후 사용자 정보 조회 API",
            description = "사용자 정보를 조회합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공",
                            content = @Content(schema = @Schema(implementation = ResponseUserInfo.class))),
            }
    )
    ResponseEntity<?> getUser(@Parameter(hidden = true) User principal);

    @Operation(
            summary = "프로필 정보 조회 API",
            description = "사용자의 프로필 정보를 조회합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공",
                            content = @Content(schema = @Schema(implementation = ResponseProfileInfo.class))),
            }
    )
    ResponseEntity<?> getProfileInfo(@Parameter(hidden = true) User principal);

    @Operation(
            summary = "프로필 정보 수정 API",
            description = "사용자의 프로필 정보를 수정합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공"),
            }
    )
    ResponseEntity<?> updateProfileInfo(@Parameter(hidden = true) User principal, @Valid @RequestBody RequestUpdateProfileInfo request);

    @Operation(
            summary = "월경 기능 ON/OFF API",
            description = "사용자의 월경 기능을 활성화합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공"),
            }
    )
    ResponseEntity<?> updateMenstrual(@Parameter(hidden = true) User principal);

    @Operation(
            summary = "회원 탈퇴 요청 API",
            description = "탈퇴 사유를 입력하여 회원 탈퇴를 진행합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공"),
            }
    )
    ResponseEntity<?> updateDeactivation(@Parameter(hidden = true) User principal, @Valid @RequestBody RequestDeactivation request);

    @Operation(
            summary = "회원 탈퇴시 할일, 기록 카운트 조회 API",
            description = "회원 탈퇴시 할일, 기록 카운트를 조회합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공",
                            content = @Content(schema = @Schema(implementation = ResponseCountTodoAndDiary.class))),
            }
    )
    ResponseEntity<?> countTodoAndDiary(@Parameter(hidden = true) User principal);

    @Operation(
            summary = "짝꿍 초대 코드 조회 API",
            description = "짝꿍 초대 코드를 조회합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공",
                            content = @Content(schema = @Schema(implementation = ResponseInviteCode.class))),
            }
    )
    ResponseEntity<?> getInviteCode(@Parameter(hidden = true) User principal);

    @Operation(
            summary = "짝꿍 초대 코드 입력 API",
            description = "짝꿍 초대 코드를 입력합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공"),
            }
    )
    ResponseEntity<?> updateInviteCode(@Parameter(hidden = true) User principal, @Valid @RequestBody RequestInviteCode request);

    @Operation(
            summary = "짝꿍 정보 조회 API",
            description = "짝꿍 정보를 조회합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공",
                            content = @Content(schema = @Schema(implementation = ResponsePairUserInfo.class))),
            }
    )
    ResponseEntity<?> getPairUserInfo(@Parameter(hidden = true) User principal);

    @Operation(
            summary = "알림 설정 조회 API",
            description = "사용자의 알림을 조회합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공",
                            content = @Content(schema = @Schema(implementation = ResponseUserNotification.class))),
            }
    )
    ResponseEntity<?> getNotification(@Parameter(hidden = true) User principal);

    @Operation(
            summary = "알림 설정 API",
            description = "사용자의 알림을 설정합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공"),
            }
    )
    ResponseEntity<?> updateNotification(@Parameter(hidden = true) User principal, @Valid @RequestBody RequestUpdateNotification request);
}
