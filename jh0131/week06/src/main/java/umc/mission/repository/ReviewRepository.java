package umc.mission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.mission.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    long countByMemberId(Long memberId);
}

