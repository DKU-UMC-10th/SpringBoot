package com.example.umc10th.domain.member.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Member {
    // 사용자 엔티티

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phoneNumber;
    private String profileUrl;
    private Integer point;
}
