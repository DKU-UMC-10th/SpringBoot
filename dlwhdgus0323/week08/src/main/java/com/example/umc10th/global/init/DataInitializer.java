package com.example.umc10th.global.init;

import com.example.umc10th.domain.member.entity.Gender;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (!memberRepository.existsByEmail("test@test.com")) {
            Member testMember = Member.builder()
                    .name("테스트유저")
                    .email("test@test.com")
                    .password(passwordEncoder.encode("test1234"))
                    .phoneNumber("010-1234-5678")
                    .address("서울시 강남구")
                    .specAddress("101호")
                    .gender(Gender.NONE)
                    .build();
            memberRepository.save(testMember);
            log.info("테스트 유저 생성 완료 - email: test@test.com / password: test1234");
        } else {
            log.info("테스트 유저가 이미 존재합니다.");
        }
    }
}
