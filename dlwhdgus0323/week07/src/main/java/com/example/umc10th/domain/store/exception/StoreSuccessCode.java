package com.example.umc10th.domain.store.exception;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum StoreSuccessCode implements BaseSuccessCode {

    WRITE_REVIEW(HttpStatus.OK, "STORE200_1", "리뷰가 성공적으로 작성되었습니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
