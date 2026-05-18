package com.hmooko.week07.domain.user.service;

import com.hmooko.week07.domain.mission.domain.MissionStatus;
import com.hmooko.week07.domain.mission.repository.UserMissionRepository;
import com.hmooko.week07.domain.review.repository.ReviewRepository;
import com.hmooko.week07.domain.user.domain.User;
import com.hmooko.week07.domain.user.dto.MyPageResponse;
import com.hmooko.week07.domain.user.repository.UserRepository;
import com.hmooko.week07.global.apiPayload.code.status.ErrorStatus;
import com.hmooko.week07.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final UserMissionRepository userMissionRepository;

    public MyPageResponse getMyPage(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.NOT_FOUND));

        return new MyPageResponse(
                user.getId(),
                user.getName(),
                user.getNickname(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getPoint(),
                reviewRepository.countByUser_Id(userId),
                userMissionRepository.countByUser_IdAndStatus(userId, MissionStatus.CHALLENGING),
                userMissionRepository.countByUser_IdAndStatus(userId, MissionStatus.COMPLETE)
        );
    }
}
