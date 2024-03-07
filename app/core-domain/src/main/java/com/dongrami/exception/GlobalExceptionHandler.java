package com.dongrami.exception;

import com.dongrami.common.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleIllegalArgsException(HttpRequestMethodNotSupportedException e) {
        log.error("{} -> {}", e.getClass().getName(), e.getMessage());

        ErrorCode errorCode = ErrorCode.METHOD_NOT_ALLOWED;

        return ResponseEntity.status(errorCode.getStatus()).body(
                ApiResponse.error(
                        errorCode.getStatus(),
                        errorCode.getCode(),
                        errorCode.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("{} -> {}", e.getClass().getName(), e.getMessage());

        ErrorCode errorCode = ErrorCode.INVALID_REQUEST;

        return ResponseEntity.status(errorCode.getStatus()).body(
                ApiResponse.error(
                        errorCode.getStatus(),
                        errorCode.getCode(),
                        errorCode.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<?> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("{} -> {}", e.getClass().getName(), e.getMessage());

        ErrorCode errorCode = ErrorCode.INVALID_REQUEST;

        return ResponseEntity.status(errorCode.getStatus()).body(
                ApiResponse.error(
                        errorCode.getStatus(),
                        errorCode.getCode(),
                        errorCode.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("{} -> {}", e.getClass().getName(), e.getMessage());

        ErrorCode errorCode = ErrorCode.INVALID_REQUEST;
        BindingResult bindingResult = e.getBindingResult();

        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errors.put(fieldError.getField(), "[" + fieldError.getRejectedValue() + "] -> " + fieldError.getDefaultMessage());
        }

        return ResponseEntity.status(errorCode.getStatus()).body(
                ApiResponse.error(
                        errorCode.getStatus(),
                        errorCode.getCode(),
                        errorCode.getMessage(),
                        errors
                )
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
