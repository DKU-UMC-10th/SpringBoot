package com.hmooko.week06.domain.user.dto;

import java.time.LocalDate;

public record UserSignupResponse(
        Long userId,
        String name,
        String sex,
        LocalDate birth,
        String address
) {
}
