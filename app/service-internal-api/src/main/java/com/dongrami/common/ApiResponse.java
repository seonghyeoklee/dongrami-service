package com.dongrami.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiResponse<T> {
    private int status;
    private String message;
    private String error;
    private T data;

    public ApiResponse(T data) {
        this.status = HttpStatus.OK.value();
        this.message = "요청이 정상 처리되었습니다.";
        this.error = null;
        this.data = data;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(data);
    }

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(null);
    }
}
