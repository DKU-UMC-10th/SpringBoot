package com.example.umc10th.domain.review.exception;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    WRITE_REVIEW(HttpStatus.OK, "REVIEW201_1", "성공적으로 리뷰가 작성되었습니다."),
    GET_MY_REVIEWS(HttpStatus.OK, "REVIEW200_1", "내 리뷰 목록 조회 성공")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
