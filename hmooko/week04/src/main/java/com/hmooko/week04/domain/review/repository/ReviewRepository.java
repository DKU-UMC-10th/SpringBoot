package com.hmooko.week04.domain.review.repository;

import com.hmooko.week04.domain.review.domain.Review;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByStore_Id(Long storeId);
    List<Review> findAllByUser_Id(Long userId);
}
