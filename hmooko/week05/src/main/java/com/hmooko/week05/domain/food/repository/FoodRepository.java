package com.hmooko.week05.domain.food.repository;

import com.hmooko.week05.domain.food.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
