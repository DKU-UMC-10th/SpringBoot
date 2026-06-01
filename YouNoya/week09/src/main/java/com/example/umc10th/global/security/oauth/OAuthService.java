package com.example.umc10th.global.security.oauth;

import com.example.umc10th.domain.member.Member;
import com.example.umc10th.domain.member.dto.MemberResponseDTO;
import com.example.umc10th.domain.member.enums.SocialType;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.global.apiPayload.code.status.ErrorStatus;
import com.example.umc10th.global.apiPayload.exception.GeneralException;
import com.example.umc10th.global.security.auth.AuthMember;
import com.example.umc10th.global.security.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OAuthService {

    private final ClientRegistrationRepository clientRegistrationRepository;
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final RestTemplate restTemplate = new RestTemplate();

    private static final String STATE_SESSION_PREFIX = "oauth_state_";

    public String buildAuthorizationUrl(String provider, HttpServletRequest request) {
        ClientRegistration registration = getRegistration(provider);

        String state = UUID.randomUUID().toString();
        request.getSession().setAttribute(STATE_SESSION_PREFIX + provider, state);

        return UriComponentsBuilder
                .fromUriString(registration.getProviderDetails().getAuthorizationUri())
                .queryParam("response_type", "code")
                .queryParam("client_id", registration.getClientId())
                .queryParam("redirect_uri", registration.getRedirectUri())
                .queryParam("scope", String.join(" ", registration.getScopes()))
                .queryParam("state", state)
                .toUriString();
    }

    public MemberResponseDTO.LoginResultDTO login(String provider, String code, String state, HttpServletRequest request) {
        String savedState = (String) request.getSession().getAttribute(STATE_SESSION_PREFIX + provider);
        if (savedState == null || !savedState.equals(state)) {
            throw new GeneralException(ErrorStatus._UNAUTHORIZED);
        }
        request.getSession().removeAttribute(STATE_SESSION_PREFIX + provider);

        ClientRegistration registration = getRegistration(provider);

        String accessToken = exchangeCodeForToken(registration, code);
        Map<String, Object> userInfo = fetchUserInfo(registration, accessToken);
        OAuthDTO oauthDto = parseUserInfo(provider, userInfo);

        Member member = memberRepository.findBySocialTypeAndSocialUid(oauthDto.getSocialType(), oauthDto.getSocialUid())
                .orElseGet(() -> memberRepository.save(
                        Member.builder()
                                .email(oauthDto.getEmail())
                                .password("OAUTH_" + oauthDto.getSocialUid())
                                .nickname(oauthDto.getName())
                                .role("ROLE_USER")
                                .socialType(oauthDto.getSocialType())
                                .socialUid(oauthDto.getSocialUid())
                                .build()
                ));

        String jwt = jwtUtil.createAccessToken(new AuthMember(member));
        return MemberResponseDTO.LoginResultDTO.builder()
                .accessToken(jwt)
                .build();
    }

    private ClientRegistration getRegistration(String provider) {
        ClientRegistration registration = clientRegistrationRepository.findByRegistrationId(provider);
        if (registration == null) {
            throw new GeneralException(ErrorStatus._BAD_REQUEST);
        }
        return registration;
    }

    @SuppressWarnings("unchecked")
    private String exchangeCodeForToken(ClientRegistration registration, String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", registration.getClientId());
        if (registration.getClientSecret() != null && !registration.getClientSecret().isEmpty()) {
            params.add("client_secret", registration.getClientSecret());
        }
        params.add("redirect_uri", registration.getRedirectUri());
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(
                registration.getProviderDetails().getTokenUri(),
                httpEntity,
                Map.class
        );

        Map<String, Object> body = response.getBody();
        if (body == null || !body.containsKey("access_token")) {
            throw new GeneralException(ErrorStatus._UNAUTHORIZED);
        }
        return (String) body.get("access_token");
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> fetchUserInfo(ClientRegistration registration, String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<Map> response = restTemplate.exchange(
                registration.getProviderDetails().getUserInfoEndpoint().getUri(),
                HttpMethod.GET,
                httpEntity,
                Map.class
        );

        Map<String, Object> body = response.getBody();
        if (body == null) {
            throw new GeneralException(ErrorStatus._UNAUTHORIZED);
        }
        return body;
    }

    @SuppressWarnings("unchecked")
    private OAuthDTO parseUserInfo(String provider, Map<String, Object> userInfo) {
        SocialType socialType = SocialType.valueOf(provider.toUpperCase());

        if (socialType == SocialType.KAKAO) {
            Map<String, Object> kakaoAccount = (Map<String, Object>) userInfo.get("kakao_account");
            Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
            String socialUid = String.valueOf(userInfo.get("id"));
            String email = (String) kakaoAccount.get("email");
            String name = (String) profile.get("nickname");
            return new KakaoDTO(socialUid, email, name);
        }

        throw new GeneralException(ErrorStatus._BAD_REQUEST);
    }
}
