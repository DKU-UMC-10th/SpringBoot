package com.example.umc10th.domain.region;

import com.example.umc10th.domain.common.BaseEntity;
import com.example.umc10th.domain.member.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "member_region_progress")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class MemberRegionProgress extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "progress_id")
    private Long id;

    @Builder.Default
    @Column(name = "completed_count", nullable = false)
    private int completedCount = 0;

    @Builder.Default
    @Column(name = "is_bonus_given", nullable = false)
    private boolean isBonusGiven = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;
}
