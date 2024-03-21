package com.dongrami.user.ui;

import com.dongrami.user.dto.UserGroupDto;
import com.dongrami.user.dto.request.RequestCreateUserGroupDto;
import com.dongrami.user.dto.request.RequestJoinUserGroupDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Tag(name = "User Group API", description = "사용자 그룹 관련 API")
public interface UserGroupControllerInterface {

    @Operation(
            summary = "사용자 그룹 조회",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공",
                            content = @Content(schema = @Schema(implementation = UserGroupDto.class))),
            }
    )
    ResponseEntity<?> getUserGroup(@AuthenticationPrincipal User principal);

    @Operation(
            summary = "사용자 그룹 생성",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공"),
            }
    )
    ResponseEntity<?> createUserGroup(@AuthenticationPrincipal User principal,
                                      @Valid @RequestBody RequestCreateUserGroupDto request);

    @Operation(
            summary = "사용자 그룹 삭제",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공"),
            }
    )
    ResponseEntity<?> deleteUserGroup(@AuthenticationPrincipal User principal);

    @Operation(
            summary = "사용자 그룹 참여하기",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공"),
            }
    )
    ResponseEntity<?> joinUserGroup(@AuthenticationPrincipal User principal,
                                    @Valid @RequestBody RequestJoinUserGroupDto request);

    @Operation(
            summary = "사용자 그룹 탈퇴하기",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공"),
            }
    )
    ResponseEntity<?> leaveUserGroup(@AuthenticationPrincipal User principal);

}
