package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberRequestDTO;
import com.example.umc10th.domain.member.dto.MemberResponseDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberRestController {

    @PostMapping("/join")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody MemberRequestDTO.JoinDTO request) {
        // 실제 로직은 Service에서 처리하므로 여기서는 가상의 성공 응답을 반환합니다.
        // 다음 주차 미션에서 Service와 Repository를 구현할 예정입니다.
        MemberResponseDTO.JoinResultDTO result = MemberResponseDTO.JoinResultDTO.builder()
                .memberId(1L)
                .createdAt(LocalDateTime.now())
                .build();

        return ApiResponse.onSuccess(result);
    }
}
