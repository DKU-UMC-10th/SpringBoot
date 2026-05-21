package com.example.umc10th.global.security.handler;

import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String xRequestedWith = request.getHeader("X-Requested-With");
        String acceptHeader = request.getHeader("Accept");

        boolean isAjax = "XMLHttpRequest".equals(xRequestedWith);
        boolean isJsonOnly = acceptHeader != null
                && acceptHeader.contains("application/json")
                && !acceptHeader.contains("text/html");
        boolean isApiRequest = uri.startsWith(contextPath + "/api/");

        if (isAjax || isJsonOnly || isApiRequest) {
            // REST API 요청 → JSON 401 응답
            ApiResponse<?> apiResponse = ApiResponse.onFailure(GeneralErrorCode.UNAUTHORIZED, null);
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
            return;
        }

        // 브라우저 페이지 접근 → /login 리다이렉트
        response.sendRedirect(contextPath + "/login");
    }
}
