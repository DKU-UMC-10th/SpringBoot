# 5주차 미션 기록

## 1. 프로젝트 세팅

- `spring-mission` 폴더에 Spring Boot 프로젝트를 새로 구성했다.
- Java 21, Spring Boot 3.3.5, Gradle 기반으로 작성했다.
- API 확인을 위해 `springdoc-openapi` 의존성을 추가했다.

## 2. 응답 통일 객체

- `global/apiPayload/ApiResponse`를 생성했다.
- 응답 구조는 워크북과 동일하게 `isSuccess`, `code`, `message`, `result` 순서로 통일했다.
- 성공 응답은 `ApiResponse.onSuccess(BaseSuccessCode, result)`, 실패 응답은 `ApiResponse.onFailure(BaseErrorCode, result)`로 생성한다.
- 성공/실패 코드는 문자열을 컨트롤러에 직접 쓰지 않고 enum으로 관리했다.

## 3. 에러 핸들링 객체

- `ProjectException`을 프로젝트 공통 예외로 만들었다.
- `GeneralExceptionAdvice`에 `@RestControllerAdvice`를 붙여 전역 예외 처리를 담당하게 했다.
- 프로젝트 예외, 검증 실패, 잘못된 요청, 지원하지 않는 메서드, 정의되지 않은 예외를 `ApiResponse` 형식으로 반환하도록 만들었다.

## 4. DTO 작성 방식

- DTO는 `record`로 작성했다.
- 이유: 요청/응답 DTO는 데이터를 전달하는 목적이 크고, `record`를 쓰면 getter, 생성자, `equals`, `hashCode`, `toString`이 자동 생성되어 보일러플레이트가 줄어든다.
- 또한 필드가 불변이라 요청/응답 값을 중간에 실수로 변경하는 일을 줄일 수 있다.

## 5. API 명세 기반 Controller, DTO 제작

첨부한 2주차 API 명세서를 기준으로 다음 API의 Controller와 DTO를 만들었다.

- `POST /api/auth/signup`: 회원가입
- `GET /api/regions/{regionId}/missions`: 지역별 미션 목록 조회
- `GET /api/users/{userId}`: 내 정보 조회
- `POST /api/stores/{storeId}/reviews`: 리뷰 작성
- `GET /api/users/{userId}/missions`: 내 미션 목록 조회
- `PATCH /api/users/{userId}/missions/{missionId}`: 미션 성공 처리

이번 주차 미션 범위가 Controller와 DTO까지이므로 Service와 Repository는 만들지 않고, Controller에서 예시 응답 데이터를 반환하도록 작성했다.
