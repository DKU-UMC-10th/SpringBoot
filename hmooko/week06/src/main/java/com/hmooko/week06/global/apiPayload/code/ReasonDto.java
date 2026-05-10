package com.hmooko.week06.global.apiPayload.code;

public record ReasonDto(
        Boolean isSuccess,
        String code,
        String message
) {
}
