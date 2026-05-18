package com.hmooko.week05.global.apiPayload.code;

public record ReasonDto(
        Boolean isSuccess,
        String code,
        String message
) {
}
