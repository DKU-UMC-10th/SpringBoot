package com.example.umc10th.domain.member.repository;

import com.example.umc10th.domain.member.Member;
import com.example.umc10th.domain.member.enums.SocialType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<Member> findBySocialTypeAndSocialUid(SocialType socialType, String socialUid);
}
