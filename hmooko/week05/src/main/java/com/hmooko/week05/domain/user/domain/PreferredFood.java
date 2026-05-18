package com.hmooko.week05.domain.user.domain;

import com.hmooko.week05.domain.food.domain.Food;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "preferred_food")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PreferredFood {

    @EmbeddedId
    private PreferredFoodId id;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @MapsId("foodId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "food_id", nullable = false)
    private Food food;

    public PreferredFood(User user, Food food) {
        this.id = new PreferredFoodId(user.getId(), food.getId());
        this.user = user;
        this.food = food;
    }
}
