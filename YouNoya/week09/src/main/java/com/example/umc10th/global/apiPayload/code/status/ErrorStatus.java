package com.example.umc10th.global.apiPayload.code.status;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th.global.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "Internal server error."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "Bad request."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "Authentication is required."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "Access is denied."),

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER4001", "Member not found."),
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4002", "Nickname does not exist."),
    MEMBER_EMAIL_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "MEMBER4003", "Email already exists."),

    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE4001", "Store not found.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}
