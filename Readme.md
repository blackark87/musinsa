# 무신사 과제
## 1. 개발 환경
- IDE: IntelliJ IDEA
- Language: Java 17
- Build Tool: Gradle
- Framework: Spring Boot 3.3.1
- Database: H2
- Test: Junit5

## 2. 구현 범위
  - Back-end : API
  - Front-end : thymeleaf
  - test : Junit5
  - API document : Swagger

## 3. 실행 방법
### 1. 프로젝트 빌드
```shell
./gradlew clean build
```

### 2. 프로젝트 실행
```shell
java -jar build/libs/musinsa-0.0.1-SNAPSHOT.jar
```

## 4. 테스트 방법

### * 주의 사항
각 한글 표현에 대해 영어로 변경하였습니다.
- 브랜드 -> brand
- 카테고리 -> category
- 상의 -> top
- 아우터 -> outer
- 바지 -> pants
- 스니커즈 -> shoes
- 가방 -> bag
- 모자 -> hat
- 양말 -> socks
- 액세서리 -> accessory

### 1. Postman
- Postman을 이용하여 API 테스트
### 2. Swagger
- Swagger를 이용하여 API 테스트
  - URL: `http://127.0.0.1:8080/swagger`
### 3. Junit5
- Junit5를 이용하여 테스트
  - Test Class: `exam.goyjin.musinsa.service.APIServiceTest`
### 4. Front-end
- 브라우저를 이용하여 테스트
  - URL: `http://127.0.0.1:8080/`


## 5. API 명세
### 1. 카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 API
- URL: `/api/lowest-price-items`
- Method: GET
- Request
    - none
- Response
  - `200 OK`
    ```json
    {
      "items": [
        {
          "brand": "C",
          "category": "top",
          "price": 10000
        },
        {
          "brand": "D",
          "category": "pants",
          "price": 3000
        },
        {
          "brand": "I",
          "category": "socks",
          "price": 1700
        },
        {
          "brand": "E",
          "category": "outer",
          "price": 5000
        },
        {
          "brand": "A",
          "category": "bag",
          "price": 2000
        },
        {
          "brand": "D",
          "category": "hat",
          "price": 1500
        },
        {
          "brand": "A",
          "category": "shoes",
          "price": 9000
        },
        {
          "brand": "F",
          "category": "accessory",
          "price": 1900
        }
    ],
    "totalPrice": 34100
    }
    ```
### 2. 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회하는 API
- URL: `/api/lowset-price-brand-items`
- Method: GET
- Request
    - none
- Response
  - `200 OK`
  ```json
  {
    "totalPrice": 36100,
    "brand": "D",
    "items": [
      {
        "brand": "D",
        "category": "top",
        "price": 10100
      },
      {
        "brand": "D",
        "category": "outer",
        "price": 5100
      },
      {
        "brand": "D",
        "category": "pants",
        "price": 3000
      },
      {
        "brand": "D",
        "category": "shoes",
        "price": 9500
      },
      {
        "brand": "D",
        "category": "bag",
        "price": 2500
      },
      {
        "brand": "D",
        "category": "hat",
        "price": 1500
      },
      {
        "brand": "D",
        "category": "socks",
        "price": 2400
      },
      {
        "brand": "D",
        "category": "accessory",
        "price": 2000
      }
    ]
  }
  ```
### 3. 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회하는 API
- URL: `/api/high-low-item/{category}`
- Method: GET
- Request
    - Path Variable
        - `id`: 상품 카테고리
- Response
  - `200 OK`
  ```json
  {
    "category": "top",
    "high": {
      "brand": "I",
      "category": "top",
      "price": 11400
    },
    "low": {
      "brand": "C",
      "category": "top",
      "price": 10000
    }
  }
  ```
### 4. 브랜드 및 상품을 추가 / 업데이트 / 삭제하는 API
#### 1. 추가
  - URL: `/api/item`
  - Method: POST
  - Request
    ```json
    {
        "brand": "Z",
        "category": "top",
        "price": 10000
    }
    ```
  - Response
    - `200 Ok`
  ```json
  {
      "status": "OK"
  }
  ```
#### 2. 삭제
  - URL: `/api/item`
  - Method: DELETE
  - Request
    ```json
    {
        "brand": "Z",
        "category": "top"
    }
    ```
  - Response
    - `200 OK'
#### 3. 수정
  - URL: `/api/item/{brand}/{category}`
  - Method: PATCH
  - Request
    ```json
    {
        "brand": "A",
        "category": "top",
        "price": 10000
    }
    ```
  - Response
    - `200 OK`