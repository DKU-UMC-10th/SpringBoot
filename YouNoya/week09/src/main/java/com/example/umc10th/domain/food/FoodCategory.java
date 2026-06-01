package com.example.umc10th.domain.food;

import com.example.umc10th.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "food_category")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class FoodCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_category_id")
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy = "foodCategory", cascade = CascadeType.ALL)
    @Builder.Default
    private List<MemberFood> memberFoodList = new ArrayList<>();
}
