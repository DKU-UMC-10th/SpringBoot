package com.example.mission.global.exception;

import com.example.mission.global.apiPayload.ApiResponse;
import com.example.mission.global.apiPayload.code.BaseErrorCode;
import com.example.mission.global.apiPayload.code.status.GeneralErrorCode;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GlobalErrorController implements ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<ApiResponse<String>> handleError(HttpServletRequest request) {
        Object statusCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object requestUri = request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);
        HttpStatus status = getHttpStatus(statusCode);
        BaseErrorCode code = status == HttpStatus.NOT_FOUND
                ? GeneralErrorCode.NOT_FOUND
                : GeneralErrorCode.INTERNAL_SERVER_ERROR;

        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onFailure(code, String.valueOf(requestUri)));
    }

    private HttpStatus getHttpStatus(Object statusCode) {
        if (statusCode instanceof Integer code) {
            return HttpStatus.resolve(code);
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
