package com.hmooko.week06.domain.review.repository;

import com.hmooko.week06.domain.review.domain.Review;
import com.hmooko.week06.domain.review.dto.StoreReviewResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    long countByUser_Id(Long userId);

    @Query("""
            select new com.hmooko.week06.domain.review.dto.StoreReviewResponse(
                r.id,
                u.id,
                u.nickname,
                r.point,
                r.content,
                r.date
            )
            from Review r
            join r.user u
            where r.store.id = :storeId
            order by r.createdAt desc
            """)
    Page<StoreReviewResponse> findReviewPageByStoreId(@Param("storeId") Long storeId, Pageable pageable);
}
