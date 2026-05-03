package com.hmooko.week05.domain.home.controller;

import com.hmooko.week05.domain.home.dto.HomeResponse;
import com.hmooko.week05.global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeController {

    @GetMapping
    public ApiResponse<HomeResponse> getHome() {
        HomeResponse response = new HomeResponse(
                "홈 화면",
                "홍길동",
                2,
                5
        );

        return ApiResponse.onSuccess(response);
    }
}
