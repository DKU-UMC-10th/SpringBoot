package com.example.mission.domain.auth.dto;

public class AuthResponseDTO {

    public record SignupResponse(
            Long userId,
            String email,
            String nickname
    ) {
    }
}
