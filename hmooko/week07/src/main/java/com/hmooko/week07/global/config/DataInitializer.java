package com.hmooko.week07.global.config;

import com.hmooko.week07.domain.mission.domain.Mission;
import com.hmooko.week07.domain.mission.domain.MissionStatus;
import com.hmooko.week07.domain.mission.domain.UserMission;
import com.hmooko.week07.domain.mission.repository.MissionRepository;
import com.hmooko.week07.domain.mission.repository.UserMissionRepository;
import com.hmooko.week07.domain.region.domain.Region;
import com.hmooko.week07.domain.region.repository.RegionRepository;
import com.hmooko.week07.domain.review.domain.Review;
import com.hmooko.week07.domain.review.repository.ReviewRepository;
import com.hmooko.week07.domain.store.domain.Store;
import com.hmooko.week07.domain.store.repository.StoreRepository;
import com.hmooko.week07.domain.user.domain.Gender;
import com.hmooko.week07.domain.user.domain.User;
import com.hmooko.week07.domain.user.repository.UserRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final RegionRepository regionRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;
    private final ReviewRepository reviewRepository;

    @Bean
    CommandLineRunner initWeek06Data() {
        return args -> {
            if (userRepository.count() > 0) {
                return;
            }

            Region seoul = regionRepository.save(Region.builder().name("서울").build());
            Region busan = regionRepository.save(Region.builder().name("부산").build());

            Store burgerHouse = storeRepository.save(Store.builder()
                    .name("버거하우스")
                    .address("서울 강남구")
                    .startTime(LocalTime.of(9, 0))
                    .endTime(LocalTime.of(22, 0))
                    .category("햄버거")
                    .region(seoul)
                    .build());

            Store pastaLab = storeRepository.save(Store.builder()
                    .name("파스타랩")
                    .address("서울 송파구")
                    .startTime(LocalTime.of(10, 0))
                    .endTime(LocalTime.of(21, 0))
                    .category("양식")
                    .region(seoul)
                    .build());

            Store fishTown = storeRepository.save(Store.builder()
                    .name("피쉬타운")
                    .address("부산 해운대구")
                    .startTime(LocalTime.of(11, 0))
                    .endTime(LocalTime.of(23, 0))
                    .category("해산물")
                    .region(busan)
                    .build());

            User hmooko = userRepository.save(User.builder()
                    .name("김현묵")
                    .nickname("hmooko")
                    .email("hmooko@umc.com")
                    .phoneNumber("010-1111-2222")
                    .gender(Gender.MALE)
                    .birthDay(LocalDate.of(2000, 6, 15))
                    .address("서울 강동구")
                    .point(2500)
                    .build());

            User reviewUser = userRepository.save(User.builder()
                    .name("리뷰어")
                    .nickname("reviewer01")
                    .email("reviewer01@umc.com")
                    .phoneNumber("010-3333-4444")
                    .gender(Gender.FEMALE)
                    .birthDay(LocalDate.of(2001, 3, 21))
                    .address("서울 송파구")
                    .point(700)
                    .build());

            Mission mission1 = missionRepository.save(Mission.builder()
                    .missionSpec("가게 메뉴에서 12,000원 이상 식사하기")
                    .accomplishedAmount(12000)
                    .accumulationPoint(500)
                    .deadline(LocalDate.now().plusDays(7))
                    .store(burgerHouse)
                    .build());

            Mission mission2 = missionRepository.save(Mission.builder()
                    .missionSpec("가게 메뉴에서 10,000원 이상 식사하기")
                    .accomplishedAmount(10000)
                    .accumulationPoint(300)
                    .deadline(LocalDate.now().plusDays(5))
                    .store(pastaLab)
                    .build());

            Mission mission3 = missionRepository.save(Mission.builder()
                    .missionSpec("가게 메뉴에서 15,000원 이상 식사하기")
                    .accomplishedAmount(15000)
                    .accumulationPoint(700)
                    .deadline(LocalDate.now().plusDays(10))
                    .store(fishTown)
                    .build());

            Mission mission4 = missionRepository.save(Mission.builder()
                    .missionSpec("가게 메뉴에서 8,000원 이상 식사하기")
                    .accomplishedAmount(8000)
                    .accumulationPoint(200)
                    .deadline(LocalDate.now().plusDays(3))
                    .store(burgerHouse)
                    .build());

            userMissionRepository.save(UserMission.builder()
                    .user(hmooko)
                    .mission(mission1)
                    .status(MissionStatus.CHALLENGING)
                    .build());

            userMissionRepository.save(UserMission.builder()
                    .user(hmooko)
                    .mission(mission2)
                    .status(MissionStatus.COMPLETE)
                    .build());

            userMissionRepository.save(UserMission.builder()
                    .user(reviewUser)
                    .mission(mission3)
                    .status(MissionStatus.CHALLENGING)
                    .build());

            reviewRepository.save(Review.builder()
                    .user(hmooko)
                    .store(burgerHouse)
                    .point(5)
                    .content("늘 너무 맛있어요. 다음에도 또 방문할 예정입니다.")
                    .date(LocalDate.now())
                    .build());

            reviewRepository.save(Review.builder()
                    .user(reviewUser)
                    .store(burgerHouse)
                    .point(4)
                    .content("가성비가 좋아서 미션 하기에도 괜찮았습니다.")
                    .date(LocalDate.now().minusDays(1))
                    .build());

            reviewRepository.save(Review.builder()
                    .user(hmooko)
                    .store(pastaLab)
                    .point(5)
                    .content("파스타 양이 많고 매장이 깔끔합니다.")
                    .date(LocalDate.now().minusDays(2))
                    .build());

            reviewRepository.save(Review.builder()
                    .user(hmooko)
                    .store(fishTown)
                    .point(4)
                    .content("재료가 신선해서 만족스러웠고 다음에도 방문하고 싶어요.")
                    .date(LocalDate.now().minusDays(3))
                    .build());
        };
    }
}
