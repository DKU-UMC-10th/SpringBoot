package com.hmooko.week07.domain.user.repository;

import com.hmooko.week07.domain.user.domain.PreferredFood;
import com.hmooko.week07.domain.user.domain.PreferredFoodId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferredFoodRepository extends JpaRepository<PreferredFood, PreferredFoodId> {

    List<PreferredFood> findAllByUser_Id(Long userId);
}
