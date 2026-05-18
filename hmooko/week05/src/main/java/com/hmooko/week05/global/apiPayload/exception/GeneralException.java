package com.hmooko.week05.global.apiPayload.exception;

import com.hmooko.week05.global.apiPayload.code.BaseCode;
import lombok.Getter;

@Getter
public class GeneralException extends RuntimeException {

    private final BaseCode code;

    public GeneralException(BaseCode code) {
        super(code.getReason().message());
        this.code = code;
    }
}
