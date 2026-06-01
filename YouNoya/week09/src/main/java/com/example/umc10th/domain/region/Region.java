package com.example.umc10th.domain.region;

import com.example.umc10th.domain.common.BaseEntity;
import com.example.umc10th.domain.store.Store;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "region")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Region extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Store> storeList = new ArrayList<>();

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    @Builder.Default
    private List<MemberRegionProgress> memberRegionProgressList = new ArrayList<>();
}
