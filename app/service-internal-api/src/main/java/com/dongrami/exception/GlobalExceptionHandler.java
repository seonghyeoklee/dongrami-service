package com.dongrami.exception;

import com.dongrami.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(
                ApiResponse.error(e.getMessage(), e.getMessage(), null)
        );
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<?> handleException(BaseException e) {
        ErrorCode errorCode = e.getErrorCode();

        return ResponseEntity.status(errorCode.getStatus()).body(
                ApiResponse.error(errorCode.getStatus(), errorCode.getCode(), errorCode.getMessage(), null)
        );
    }


}
