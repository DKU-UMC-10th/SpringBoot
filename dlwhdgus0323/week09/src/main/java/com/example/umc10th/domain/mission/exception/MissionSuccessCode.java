package com.example.umc10th.domain.mission.exception;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    GET_MEMBER_MISSIONS(HttpStatus.OK, "MISSION200_1", "내 미션 목록 조회 성공"),
    COMPLETE_MISSION(HttpStatus.OK, "MISSION200_2", "성공적으로 미션 성공 처리되었습니다."),
    GET_HOME_MISSIONS(HttpStatus.OK, "MISSION200_3", "홈 화면 미션 목록 조회 성공"),
    GET_MY_MISSIONS(HttpStatus.OK, "MISSION200_4", "내 진행중인 미션 목록 조회 성공")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
