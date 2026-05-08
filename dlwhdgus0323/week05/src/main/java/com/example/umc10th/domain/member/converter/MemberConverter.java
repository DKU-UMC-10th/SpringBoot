package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.domain.Member;
import com.example.umc10th.domain.member.dto.response.MemberResDTO;

public class MemberConverter {

    public static MemberResDTO.GetInfo toGetInfo(Member member) {
        return MemberResDTO.GetInfo.builder()
                .email(member.getEmail())
                .name(member.getName())
                .point(member.getPoint())
                .phoneNumber(member.getPhoneNumber())
                .profileUrl(member.getProfileUrl())
                .build();
    }
}
