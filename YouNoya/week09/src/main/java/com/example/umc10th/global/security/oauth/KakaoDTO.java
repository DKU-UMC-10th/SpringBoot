package com.example.umc10th.global.security.oauth;

import com.example.umc10th.domain.member.enums.SocialType;

public record KakaoDTO(String socialUid, String email, String name) implements OAuthDTO {

    @Override
    public String getSocialUid() { return socialUid; }

    @Override
    public String getEmail() { return email; }

    @Override
    public String getName() { return name; }

    @Override
    public SocialType getSocialType() { return SocialType.KAKAO; }
}
