# Budget-Guardian
예산 관리 서비스 💸

## 개요
본 서비스는 사용자들이 개인 재무를 관리하고 지출을 추적하는 데 도움을 주는 애플리케이션입니다.  
이 앱은 사용자들이 예산을 설정하고 지출을 모니터링하며 재무 목표를 달성하게끔 해줍니다.

[기업 과제 상세 설명](https://bow-hair-db3.notion.site/90cba97a58a843e4a2563a226db3d5b5)

<br />

## 개발 환경
```
• IDE : IntelliJ IDEA Ultimate
• 언어 : Java 17
• 프레임워크 : Spring Boot 3.1.5
• 빌드 도구 : Gradle
• 데이터베이스 : MySQL 8.0 + Docker Build
```

## 사용 기술
```
- Java / Spring / JPA
- Authorization : Spring Security, Jwt
- DOCS : Swagger
- DB : MySql, Redis
- Test: junit, Mockito
```
<br />

## 서버 아키텍쳐
<img width="1165" alt="image" src="https://github.com/min050410/Budget-Guardian/assets/45661217/2dbd3c79-2a32-4ee0-947c-6fce23a065b3">

## ERD
<img src = "https://github.com/min050410/Budget-Guardian/assets/45661217/c6437f87-a5af-492a-9cbb-deb65d381bfc" width="500" />

<br />

## UseCase

1. 유저는 본 사이트에 들어와 회원가입을 통해 서비스를 이용합니다.
2. 예산 설정 및 설계 서비스
    - 2-1. **월별** 총 예산을 설정합니다.
    - 2-2. 본 서비스는 **카테고리** 별 예산을 설계(=추천)하여 사용자의 과다 지출을 방지합니다.
3. 지출 기록
    - 3-1. 사용자는 **지출** 을  **금액**, **카테고리** 등을 지정하여 등록 합니다. 언제든지 수정 및 삭제 할 수 있습니다.
4. 지출 컨설팅
    - 4-1. **월별** 설정한 예산을 기준으로 오늘 소비 가능한 **지출** 을 알려줍니다.
    - 4-2. 매일 발생한 **지출** 을 **카테고리** 별로 안내받습니다.
5. 지출 통계
    - 5-1. **지난 달 대비** , **지난 요일 대비**,  **다른 유저 대비** 등 여러 기준 **카테고리 별** 지출 통계를 확인 할 수 있습니다.

## API

#### Member(사용자, 인증)

<img width="1432" alt="image" src="https://github.com/min050410/Budget-Guardian/assets/45661217/17b4af4a-8bb1-41d9-aacc-0c892d073249">


#### Category(카테고리)

<img width="1430" alt="image" src="https://github.com/min050410/Budget-Guardian/assets/45661217/179767e8-8e84-431c-88ea-3fb86a87fc32">


#### Budget(예산)

<img width="1433" alt="image" src="https://github.com/min050410/Budget-Guardian/assets/45661217/8ccc9d1f-fec1-4fbb-8eb2-2bb0e29261f4">


#### Expenditdure(지출)

<img width="1433" alt="image" src="https://github.com/min050410/Budget-Guardian/assets/45661217/9647f4ee-8a2d-43aa-b8fd-8994d9ed7310">
