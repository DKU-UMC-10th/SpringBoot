package com.hmooko.week07.global.apiPayload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hmooko.week07.global.apiPayload.code.BaseCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse<T> {

    private final Boolean isSuccess;
    private final String code;
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T result;

    public static <T> ApiResponse<T> onSuccess(T result) {
        return new ApiResponse<>(true, "COMMON200", "요청에 성공했습니다.", result);
    }

    public static <T> ApiResponse<T> of(BaseCode code, T result) {
        return new ApiResponse<>(
                code.getReason().isSuccess(),
                code.getReason().code(),
                code.getReason().message(),
                result
        );
    }

    public static <T> ApiResponse<T> onFailure(BaseCode code, T result) {
        return new ApiResponse<>(
                code.getReason().isSuccess(),
                code.getReason().code(),
                code.getReason().message(),
                result
        );
    }
}
