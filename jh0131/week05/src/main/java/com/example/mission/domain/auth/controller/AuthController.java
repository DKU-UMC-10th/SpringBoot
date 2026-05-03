package com.example.mission.domain.auth.controller;

import com.example.mission.domain.auth.code.AuthSuccessCode;
import com.example.mission.domain.auth.dto.AuthRequestDTO;
import com.example.mission.domain.auth.dto.AuthResponseDTO;
import com.example.mission.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<AuthResponseDTO.SignupResponse>> signup(
            @Valid @RequestBody AuthRequestDTO.SignupRequest request
    ) {
        AuthResponseDTO.SignupResponse response = new AuthResponseDTO.SignupResponse(
                1L,
                request.email(),
                request.nickname()
        );

        return ResponseEntity.status(AuthSuccessCode.SIGNUP.getStatus())
                .body(ApiResponse.onSuccess(AuthSuccessCode.SIGNUP, response));
    }
}
