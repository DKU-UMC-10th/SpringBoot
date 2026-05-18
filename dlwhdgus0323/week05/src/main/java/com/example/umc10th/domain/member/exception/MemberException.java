package com.example.umc10th.domain.member.exception;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th.global.apiPayload.exception.ProjectException;

public class MemberException extends ProjectException {
    // 사용자 도메인 전용 예외 처리
    public MemberException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
