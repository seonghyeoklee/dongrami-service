package com.dongrami.exception;

import com.dongrami.common.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleIllegalArgsException(HttpRequestMethodNotSupportedException e) {
        log.error("{} -> {}", e.getClass().getName(), e.getMessage());

        return ResponseEntity.badRequest().body(
                ApiResponse.error(HttpStatus.BAD_REQUEST.value(), ErrorCode.METHOD_NOT_ALLOWED.getCode(), ErrorCode.METHOD_NOT_ALLOWED.getMessage(), null)
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("{} -> {}", e.getClass().getName(), e.getMessage());

        return ResponseEntity.badRequest().body(
                ApiResponse.error(HttpStatus.BAD_REQUEST.value(), ErrorCode.INVALID_PARAMETER.getCode(), ErrorCode.INVALID_PARAMETER.getMessage(), null)
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleException(IllegalArgumentException e) {
        log.error("{} -> {}", e.getClass().getName(), e.getMessage());

        return ResponseEntity.badRequest().body(
                ApiResponse.error(e.getMessage(), e.getMessage(), null)
        );
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<?> handleException(BaseException e) {
        log.error("{} -> {}", e.getClass().getName(), e.getMessage());
        ErrorCode errorCode = e.getErrorCode();

        return ResponseEntity.status(errorCode.getStatus()).body(
                ApiResponse.error(errorCode.getStatus(), errorCode.getCode(), errorCode.getMessage(), null)
        );
    }

}
