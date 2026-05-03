package com.example.mission.domain.user.dto;

public class UserResponseDTO {

    public record UserInfoResponse(
            Long userId,
            String nickname,
            String profileUrl,
            String email,
            String phoneNumber,
            Integer point
    ) {
    }
}
