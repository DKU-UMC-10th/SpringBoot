package com.example.umc10th.domain.region;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "region")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;
}
