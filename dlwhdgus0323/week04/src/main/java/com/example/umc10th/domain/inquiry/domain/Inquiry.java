package com.example.umc10th.domain.inquiry.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Inquiry {
    // 문의 엔티티

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
