package com.hmooko.week07.global.apiPayload.code;

public record ReasonDto(
        Boolean isSuccess,
        String code,
        String message
) {
}
