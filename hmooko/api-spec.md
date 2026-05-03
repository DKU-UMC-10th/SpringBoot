# API 명세서

이미지에 정리된 내용을 바탕으로 작성한 초안 명세서입니다.

## 공통 사항

- Base Path: `/api`
- 인증 방식: `Authorization: Bearer {accessToken}`
- 응답 포맷:

```json
{
  "isSuccess": true,
  "code": "COMMON200",
  "message": "요청에 성공했습니다.",
  "result": {}
}
```

## 1. 홈 화면

- 이름: 홈 화면
- HTTP Method: `GET`
- API Path: `/api`
- Request Header:

```http
Authorization: Bearer {accessToken}
```

- Request Body: 없음
- Query String: 없음
- Path Variable: 없음

## 2. 마이 페이지 리뷰 작성

- 이름: 마이 페이지 리뷰 작성
- HTTP Method: `POST`
- API Path: `/api/reviews`
- Request Header:

```http
Authorization: Bearer {accessToken}
```

- Path Variable:
  - `storeId`
- Request Body:

```json
{
  "stars": 4,
  "content": "이 집 잘하네!",
  "images": "이미지 파일"
}
```

- Query String: 없음
- 호출 예시:

```http
POST /api/reviews/{storeId}
Authorization: Bearer {accessToken}
Content-Type: multipart/form-data 또는 application/json
```

## 3. 미션 목록 조회

- 이름: 미션 목록 조회
- HTTP Method: `GET`
- API Path: `/api/missions`
- Request Header:

```http
Authorization: Bearer {accessToken}
```

- Request Body: 없음
- Query String:
  - `status=진행중`
  - `status=성공`
- Path Variable: 없음
- 호출 예시:

```http
GET /api/missions?status=진행중
GET /api/missions?status=성공
```

## 4. 미션 성공 누르기

- 이름: 미션 성공 누르기
- HTTP Method: `PATCH`
- API Path: `/api/missions/success`
- Request Header:

```http
Authorization: Bearer {accessToken}
```

- Request Body: 없음
- Query String: 없음
- Path Variable:
  - `missionId`
- 호출 예시:

```http
PATCH /api/missions/success/{missionId}
Authorization: Bearer {accessToken}
```

## 5. 회원 가입 하기

- 이름: 회원 가입 하기
- HTTP Method: `POST`
- API Path: `/api/users/signup`
- Request Header: 없음
- Path Variable: 없음
- Query String: 없음
- Request Body:

```json
{
  "name": "구현모",
  "sex": "남성",
  "birth": "2001-05-06",
  "address": "경기도"
}
```

## 비고

- 본 문서는 이미지의 표 내용을 기준으로 작성했습니다.
- `storeId`, `missionId`는 표에서 Path variable 컬럼으로 분리되어 있어 호출 예시는 일반적인 REST 형태로 보완했습니다.
- `images` 필드는 이미지 파일 업로드를 의미하므로 실제 구현 시 `multipart/form-data` 여부를 확정해야 합니다.
