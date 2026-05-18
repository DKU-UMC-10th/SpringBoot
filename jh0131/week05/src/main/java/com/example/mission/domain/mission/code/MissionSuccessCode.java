package com.example.mission.domain.mission.code;

import com.example.mission.global.apiPayload.code.BaseSuccessCode;
import org.springframework.http.HttpStatus;

public enum MissionSuccessCode implements BaseSuccessCode {
    REGION_MISSIONS(HttpStatus.OK, "MISSION200_1", "성공적으로 지역별 미션 목록을 조회했습니다."),
    USER_MISSIONS(HttpStatus.OK, "MISSION200_2", "성공적으로 내 미션 목록을 조회했습니다."),
    COMPLETE_MISSION(HttpStatus.OK, "MISSION200_3", "성공적으로 미션을 완료 처리했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    MissionSuccessCode(HttpStatus status, String code, String message) {
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
