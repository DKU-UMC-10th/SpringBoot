package com.example.umc10th.global.security.oauth;

import com.example.umc10th.domain.member.dto.MemberResponseDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
public class OAuthController {

    private final OAuthService oAuthService;

    @GetMapping("/authorize/{provider}")
    public void authorize(
            @PathVariable String provider,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        String redirectUrl = oAuthService.buildAuthorizationUrl(provider, request);
        response.sendRedirect(redirectUrl);
    }

    @GetMapping("/callback/{provider}")
    public ApiResponse<MemberResponseDTO.LoginResultDTO> callback(
            @PathVariable String provider,
            @RequestParam String code,
            @RequestParam String state,
            HttpServletRequest request
    ) {
        return ApiResponse.onSuccess(oAuthService.login(provider, code, state, request));
    }
}
