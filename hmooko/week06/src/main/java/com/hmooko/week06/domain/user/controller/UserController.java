package com.hmooko.week06.domain.user.controller;

import com.hmooko.week06.domain.user.dto.MyPageResponse;
import com.hmooko.week06.domain.user.dto.UserSignupRequest;
import com.hmooko.week06.domain.user.dto.UserSignupResponse;
import com.hmooko.week06.domain.user.service.UserService;
import com.hmooko.week06.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

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

    @GetMapping("/members/{userId}/my-page")
    public ApiResponse<MyPageResponse> getMyPage(@PathVariable Long userId) {
        return ApiResponse.onSuccess(userService.getMyPage(userId));
    }
}
