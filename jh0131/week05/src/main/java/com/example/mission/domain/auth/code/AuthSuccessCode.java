package com.example.mission.domain.auth.code;

import com.example.mission.global.apiPayload.code.BaseSuccessCode;
import org.springframework.http.HttpStatus;

public enum AuthSuccessCode implements BaseSuccessCode {
    SIGNUP(HttpStatus.CREATED, "AUTH201_1", "회원가입에 성공했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    AuthSuccessCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
