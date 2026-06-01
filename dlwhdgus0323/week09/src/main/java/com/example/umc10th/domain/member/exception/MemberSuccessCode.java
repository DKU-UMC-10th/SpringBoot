package com.example.umc10th.domain.member.exception;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    SIGN_UP(HttpStatus.OK, "MEMBER201_1", "성공적으로 회원가입이 완료되었습니다."),
    LOGIN(HttpStatus.OK, "MEMBER200_3", "성공적으로 로그인 되었습니다."),
    OK(HttpStatus.OK, "MEMBER200_1", "성공적으로 유저를 조회했습니다."),
    HOME(HttpStatus.OK, "MEMBER200_2", "성공적으로 홈화면 조회했습니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
