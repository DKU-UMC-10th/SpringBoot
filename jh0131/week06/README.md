# 6주차 JPA 미션 구현 파일

현재 작업공간은 Spring Boot 프로젝트가 아니라서, 바로 붙여 넣어 사용할 수 있는 Spring Boot/JPA 예시 파일 묶음으로 작성했습니다.

## 미션 범위

- 엔티티 제작 및 연관관계 매핑
- 리뷰 작성 API 서비스
- 마이페이지 조회 API 서비스
- 내가 진행중/완료한 미션 조회 API 서비스, 페이징 포함
- 홈 화면 미션 목록 조회 API 서비스, 페이징 포함
- 페이징 조회는 `@Query` 기반 Repository 메서드로 작성

## 옮기는 방법

1. 실제 Spring Boot 프로젝트의 기본 패키지명에 맞게 `package umc.mission...` 부분을 수정합니다.
2. `Application` 클래스에 `@EnableJpaAuditing`을 추가합니다.
3. `spring-boot-starter-data-jpa`, `lombok`, `mysql-connector-j` 의존성이 필요합니다.
4. 기존 5주차 컨트롤러가 있다면 `MissionController`의 메서드 내용을 기존 컨트롤러로 옮겨도 됩니다.

## API 예시

```http
POST /api/stores/{storeId}/reviews
GET /api/members/{memberId}/my-page
GET /api/members/{memberId}/missions?status=CHALLENGING&page=0&size=10
GET /api/home/missions?regionId=1&page=0&size=10
```

## 구현 메모

- `MemberMission`은 회원과 미션의 중간 테이블입니다.
- `MissionStatus`로 진행중/완료 상태를 구분합니다.
- `Review`는 사진을 제외하고 별점, 내용, 회원, 가게만 저장합니다.
- 홈 화면은 현재 선택 지역의 가게들 중 도전 가능한 미션을 조회합니다.
- LAZY 로딩을 기본으로 두고, 화면에 필요한 조회는 Repository의 JPQL에서 DTO로 바로 내려줍니다.

