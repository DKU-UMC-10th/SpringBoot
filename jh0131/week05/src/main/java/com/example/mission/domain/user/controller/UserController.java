package com.example.mission.domain.user.controller;

import com.example.mission.domain.user.code.UserSuccessCode;
import com.example.mission.domain.user.dto.UserResponseDTO;
import com.example.mission.global.apiPayload.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserResponseDTO.UserInfoResponse>> getUserInfo(
            @PathVariable Long userId
    ) {
        UserResponseDTO.UserInfoResponse response = new UserResponseDTO.UserInfoResponse(
                userId,
                "nickname012",
                "https://example.com/profile.png",
                "dlapdlf@naver.com",
                null,
                2500
        );

        return ResponseEntity.status(UserSuccessCode.GET_USER.getStatus())
                .body(ApiResponse.onSuccess(UserSuccessCode.GET_USER, response));
    }
}
