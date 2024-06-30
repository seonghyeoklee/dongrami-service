package com.dongrami.user.ui;

import com.dongrami.user.dto.request.RequestLogin;
import com.dongrami.user.dto.response.ResponseLoginSuccess;
import com.dongrami.user.dto.response.ResponseRefreshToken;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Tag(name = "Auth API", description = "인증 API")
public interface AuthControllerInterface {

    @Operation(
            summary = "로그인 API",
            description = "서비스 이용을 위한 로그인을 시도합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공",
                            content = @Content(schema = @Schema(implementation = ResponseLoginSuccess.class))),
            }
    )
    ResponseEntity<?> login(HttpServletRequest request, HttpServletResponse response, @Valid @RequestBody RequestLogin requestLogin);

    @Operation(
            summary = "토큰 갱신 API",
            description = "accessToken 만료 시 refreshToken을 이용하여 accessToken을 재발급합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공",
                            content = @Content(schema = @Schema(implementation = ResponseRefreshToken.class))),
            }
    )
    ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response);
}
