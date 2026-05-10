package com.example.umc10th.domain.member.repository;

import com.example.umc10th.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // DB 접근 interface
}
