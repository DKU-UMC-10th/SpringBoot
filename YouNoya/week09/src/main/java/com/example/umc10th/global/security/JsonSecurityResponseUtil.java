package com.example.umc10th.global.security;

import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.status.ErrorStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JsonSecurityResponseUtil {

    private final ObjectMapper objectMapper;

    public void writeErrorResponse(HttpServletResponse response, ErrorStatus errorStatus) throws IOException {
        response.setStatus(errorStatus.getHttpStatus().value());
        response.setContentType("application/json;charset=UTF-8");
        objectMapper.writeValue(
                response.getOutputStream(),
                ApiResponse.onFailure(errorStatus.getCode(), errorStatus.getMessage(), null)
        );
    }
}
