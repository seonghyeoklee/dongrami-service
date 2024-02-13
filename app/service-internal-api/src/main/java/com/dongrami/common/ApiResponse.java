package com.dongrami.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiResponse<T> {
    private int status;
    private String error;
    private String message;
    private T data;

    public ApiResponse(HttpStatus httpStatus, T data) {
        this.status = httpStatus.value();
        this.error = null;
        this.message = null;
        this.data = data;
    }

    public ApiResponse(HttpStatus httpStatus, String error, String message, T data) {
        this.status = httpStatus.value();
        this.error = error;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(HttpStatus.OK, data);
    }

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(HttpStatus.OK, null);
    }

    public static <T> ApiResponse<T> error(String error, String message, T data) {
        return new ApiResponse<>(HttpStatus.BAD_REQUEST, error, message, data);
    }

}
