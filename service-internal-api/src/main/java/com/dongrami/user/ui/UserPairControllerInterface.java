package com.dongrami.user.ui;

import com.dongrami.user.dto.request.RequestInviteCode;
import com.dongrami.user.dto.response.ResponseInviteCode;
import com.dongrami.user.dto.response.ResponsePairUserInfo;
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

@Tag(name = "짝꿍 API", description = "사용자 짝꿍 관련 API")
public interface UserPairControllerInterface {

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
            summary = "짝꿍 해제 API",
            description = "짝꿍을 해제합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공"),
            }
    )
    ResponseEntity<?> deletePairUser(@Parameter(hidden = true) User principal);
}
