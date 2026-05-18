package com.hmooko.week05.domain.comment.repository;

import com.hmooko.week05.domain.comment.domain.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByReview_Id(Long reviewId);
}
