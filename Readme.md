# 무신사 과제
## 1. 개발 환경
- IDE: IntelliJ IDEA
- Language: Java 17
- Build Tool: Gradle
- Framework: Spring Boot 3.3.1
- Database: H2
- Test: Junit5

## 2. 실행 방법
1. 프로젝트를 다운로드 받습니다.
2. IntelliJ IDEA에서 프로젝트를 Open 합니다.
3. `src/main/java/com/musinsa/` 패키지에 있는 `MusinsaApplication.java` 파일을 실행합니다.
4. `http://localhost:8080`으로 접속합니다.

## 3. API 명세
### 1. 카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 API
- URL: `/api/products`
- Method: GET
- Request
    - Query Parameter
        - `page`: 페이지 번호
        - `size`: 페이지 크기
- Response
    - `200 OK`
    ```json
    {
        "content": [
            {
                "id": 1,
                "name": "상품1",
                "price": 10000,
                "discountRate": 10,
                "discountedPrice": 9000
            },
            {
                "id": 2,
                "name": "상품2",
                "price": 20000,
                "discountRate": 20,
                "discountedPrice": 16000
            }
        ],
        "pageable": {
            "page": 0,
            "size": 10
        }
    }
    ```
### 2. 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회하는 API
- URL: `/api/products/{id}`
- Method: GET
- Request
    - Path Variable
        - `id`: 상품 ID
- Response
  - `200 OK`
  ```json
  {
      "id": 1,
      "name": "상품1",
      "price": 10000,
      "discountRate": 10,
      "discountedPrice": 9000
  }
  ```
### 3. 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회하는 API
- URL: `/api/products/{id}`
- Method: GET
- Request
    - Path Variable
        - `id`: 상품 ID
- Response
  - `200 OK`
  ```json
  {
      "id": 1,
      "name": "상품1",
      "price": 10000,
      "discountRate": 10,
      "discountedPrice": 9000
  }
  ```
### 4. 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회하는 API
- URL: `/api/products/{id}`
- Method: GET
- Request
    - Path Variable
        - `id`: 상품 ID
- Response
  - `200 OK`
  ```json
  {
      "id": 1,
      "name": "상품1",
      "price": 10000,
      "discountRate": 10,
      "discountedPrice": 9000
  }
  ```