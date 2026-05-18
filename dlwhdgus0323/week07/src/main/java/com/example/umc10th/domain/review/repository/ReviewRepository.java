package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // ID 순 - 첫 페이지 (커서 없음)
    Slice<Review> findByMemberIdOrderByIdDesc(Long memberId, Pageable pageable);

    // ID 순 - 커서 있음
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId AND r.id < :cursorId ORDER BY r.id DESC")
    Slice<Review> findByMemberIdAndIdLessThan(
            @Param("memberId") Long memberId,
            @Param("cursorId") Long cursorId,
            Pageable pageable);

    // 별점 순 - 첫 페이지 (커서 없음)
    Slice<Review> findByMemberIdOrderByScoreDescIdDesc(Long memberId, Pageable pageable);

    // 별점 순 - 커서 있음 (별점 동점 시 ID로 tie-break)
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId " +
           "AND (r.score < :score OR (r.score = :score AND r.id < :cursorId)) " +
           "ORDER BY r.score DESC, r.id DESC")
    Slice<Review> findByMemberIdWithStarCursor(
            @Param("memberId") Long memberId,
            @Param("score") Float score,
            @Param("cursorId") Long cursorId,
            Pageable pageable);
}
