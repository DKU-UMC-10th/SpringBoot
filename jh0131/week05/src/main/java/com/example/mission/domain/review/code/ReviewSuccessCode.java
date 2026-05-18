package com.example.mission.domain.review.code;

import com.example.mission.global.apiPayload.code.BaseSuccessCode;
import org.springframework.http.HttpStatus;

public enum ReviewSuccessCode implements BaseSuccessCode {
    CREATE_REVIEW(HttpStatus.CREATED, "REVIEW201_1", "성공적으로 리뷰를 작성했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    ReviewSuccessCode(HttpStatus status, String code, String message) {
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
