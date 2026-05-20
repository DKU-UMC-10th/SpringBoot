package com.hmooko.week06.global.apiPayload.exception.handler;

import com.hmooko.week06.global.apiPayload.ApiResponse;
import com.hmooko.week06.global.apiPayload.code.status.ErrorStatus;
import com.hmooko.week06.global.apiPayload.exception.GeneralException;
import jakarta.validation.ConstraintViolationException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiResponse<Void>> handleGeneralException(GeneralException exception) {
        ErrorStatus errorStatus = exception.getCode() instanceof ErrorStatus status
                ? status
                : ErrorStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity
                .status(errorStatus.getHttpStatus())
                .body(ApiResponse.onFailure(errorStatus, null));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception
    ) {
        Map<String, String> errors = new LinkedHashMap<>();

        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity
                .status(ErrorStatus.VALIDATION_ERROR.getHttpStatus())
                .body(ApiResponse.onFailure(ErrorStatus.VALIDATION_ERROR, errors));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleConstraintViolationException(
            ConstraintViolationException exception
    ) {
        Map<String, String> errors = new LinkedHashMap<>();

        exception.getConstraintViolations()
                .forEach(violation -> errors.put(violation.getPropertyPath().toString(), violation.getMessage()));

        return ResponseEntity
                .status(ErrorStatus.VALIDATION_ERROR.getHttpStatus())
                .body(ApiResponse.onFailure(ErrorStatus.VALIDATION_ERROR, errors));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception exception) {
        return ResponseEntity
                .status(ErrorStatus.INTERNAL_SERVER_ERROR.getHttpStatus())
                .body(ApiResponse.onFailure(ErrorStatus.INTERNAL_SERVER_ERROR, null));
    }
}
