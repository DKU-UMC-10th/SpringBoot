package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.review.dto.request.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.response.ReviewResDTO;

public interface ReviewService {

    ReviewResDTO.WriteReview writeReview(Long userId, ReviewReqDTO.WriteReview request);
}
