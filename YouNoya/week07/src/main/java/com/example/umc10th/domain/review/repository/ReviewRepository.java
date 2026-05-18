package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // ID 순 - 첫 페이지
    Slice<Review> findByMemberIdOrderByIdDesc(Long memberId, Pageable pageable);

    // ID 순 - 커서 이후 (id < cursorId)
    Slice<Review> findByMemberIdAndIdLessThanOrderByIdDesc(Long memberId, Long cursorId, Pageable pageable);

    // 별점 순 - 첫 페이지
    Slice<Review> findByMemberIdOrderByRatingDescIdDesc(Long memberId, Pageable pageable);

    // 별점 순 - 커서 이후 (rating < cursorRating OR (rating = cursorRating AND id < cursorId))
    @Query("SELECT r FROM Review r " +
           "WHERE r.member.id = :memberId " +
           "AND (r.rating < :cursorRating OR (r.rating = :cursorRating AND r.id < :cursorId)) " +
           "ORDER BY r.rating DESC, r.id DESC")
    Slice<Review> findByMemberIdAfterRatingCursor(@Param("memberId") Long memberId,
                                                   @Param("cursorRating") Float cursorRating,
                                                   @Param("cursorId") Long cursorId,
                                                   Pageable pageable);
}
