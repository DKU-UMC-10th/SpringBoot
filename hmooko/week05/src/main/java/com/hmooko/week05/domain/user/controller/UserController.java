package com.hmooko.week05.domain.user.controller;

import com.hmooko.week05.domain.user.dto.UserSignupRequest;
import com.hmooko.week05.domain.user.dto.UserSignupResponse;
import com.hmooko.week05.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @PostMapping("/signup")
    public ApiResponse<UserSignupResponse> signup(@Valid @RequestBody UserSignupRequest request) {
        UserSignupResponse response = new UserSignupResponse(
                1L,
                request.getName(),
                request.getSex().getDisplayName(),
                request.getBirth(),
                request.getAddress()
        );

        return ApiResponse.onSuccess(response);
    }
}
