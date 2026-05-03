package com.hmooko.week05.domain.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PreferredFoodId implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "food_id")
    private Long foodId;

    public PreferredFoodId(Long userId, Long foodId) {
        this.userId = userId;
        this.foodId = foodId;
    }
}
