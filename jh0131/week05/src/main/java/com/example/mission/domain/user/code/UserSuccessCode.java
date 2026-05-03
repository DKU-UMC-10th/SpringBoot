package com.example.mission.domain.user.code;

import com.example.mission.global.apiPayload.code.BaseSuccessCode;
import org.springframework.http.HttpStatus;

public enum UserSuccessCode implements BaseSuccessCode {
    GET_USER(HttpStatus.OK, "USER200_1", "성공적으로 내 정보를 조회했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    UserSuccessCode(HttpStatus status, String code, String message) {
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
