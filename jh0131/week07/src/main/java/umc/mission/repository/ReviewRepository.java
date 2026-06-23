package umc.mission.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.mission.domain.Review;
import umc.mission.web.dto.MyReviewResponse;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    long countByMemberId(Long memberId);

    @Query("""
            select new umc.mission.web.dto.MyReviewResponse(
                r.id,
                s.name,
                r.score,
                r.body
            )
            from Review r
            join r.store s
            where r.member.id = :memberId
              and (:cursorId is null or r.id < :cursorId)
            order by r.id desc
            """)
    Slice<MyReviewResponse> findMyReviewsByIdCursor(
            @Param("memberId") Long memberId,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );

    @Query("""
            select new umc.mission.web.dto.MyReviewResponse(
                r.id,
                s.name,
                r.score,
                r.body
            )
            from Review r
            join r.store s
            where r.member.id = :memberId
              and (
                  :cursorScore is null
                  or r.score < :cursorScore
                  or (r.score = :cursorScore and r.id < :cursorId)
              )
            order by r.score desc, r.id desc
            """)
    Slice<MyReviewResponse> findMyReviewsByScoreCursor(
            @Param("memberId") Long memberId,
            @Param("cursorScore") Float cursorScore,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );
}
