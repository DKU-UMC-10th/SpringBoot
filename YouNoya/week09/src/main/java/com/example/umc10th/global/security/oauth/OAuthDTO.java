package com.example.umc10th.global.security.oauth;

import com.example.umc10th.domain.member.enums.SocialType;

public interface OAuthDTO {
    String getSocialUid();
    String getEmail();
    String getName();
    SocialType getSocialType();
}
