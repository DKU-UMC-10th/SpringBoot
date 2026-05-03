# Spring Mission

5주차 스프링 스터디 미션용 프로젝트입니다.

## 구현 범위

- 응답 통일 객체: `ApiResponse`
- 성공/실패 코드 인터페이스: `BaseSuccessCode`, `BaseErrorCode`
- 공통 성공/실패 enum: `GeneralSuccessCode`, `GeneralErrorCode`
- 프로젝트 공통 예외: `ProjectException`
- 전역 예외 핸들러: `GeneralExceptionAdvice`, `GlobalErrorController`
- 2주차 API 명세 기반 Controller, DTO

## API 목록

| Method | URL | 설명 |
| --- | --- | --- |
| POST | `/api/auth/signup` | 회원가입 |
| GET | `/api/regions/{regionId}/missions` | 지역별 미션 목록 조회 |
| GET | `/api/users/{userId}` | 내 정보 조회 |
| POST | `/api/stores/{storeId}/reviews` | 리뷰 작성 |
| GET | `/api/users/{userId}/missions` | 내 미션 목록 조회 |
| PATCH | `/api/users/{userId}/missions/{missionId}` | 미션 성공 처리 |

## 실행 방법

로컬에 Gradle이 설치되어 있다면 아래 명령으로 실행할 수 있습니다.

```bash
gradle bootRun
```

실행 후 Swagger는 아래 주소에서 확인할 수 있습니다.

```text
http://localhost:8080/swagger-ui.html
```

## 참고

이번 미션 범위는 Controller와 DTO 제작까지라서 Service와 Repository는 만들지 않았습니다.
각 Controller는 Swagger 테스트가 가능하도록 임시 응답 데이터를 반환합니다.
