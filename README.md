# JPA N+1 Problem

이 프로젝트는 Spring Boot와 JPA, H2 DB를 활용하여 N+1 문제를 발생시키고 해결하는 프로젝트입니다. N+1 문제 해결은 EntityGraph, BatchSize, QueryProtection 으로 진행합니다.

## 프로젝트 구조

```
test/
├── src/main/java/com/jpa/api/
│   ├── ApiApplication.java          # 메인 애플리케이션 클래스
│   ├── global/                      # 글로벌 설정 및 공통 모듈
│   │   ├── config/                  # 설정 클래스
│   │   ├── entity/                  # 공통 엔티티 (BaseEntity)
│   │   ├── enums/                   # 공통 열거형
│   │   ├── exception/               # 예외 처리
│   │   └── service/                 # 초기 데이터 생성 서비스
│   ├── member/                      # 멤버 관련 모듈
│   │   ├── controller/              # REST 컨트롤러
│   │   ├── model/                   # DTO, 엔티티, 서비스, 리포지토리
│   │   └── repository/              # JPA 리포지토리
│   └── team/                        # 팀 관련 모듈
│       ├── controller/
│       ├── model/
│       └── repository/
├── src/main/resources/
│   └── application.yaml             # 애플리케이션 설정
└── src/test/java/                   # 테스트 코드
```

## 주요 기능

- **멤버 관리**: 멤버 생성, 조회, 수정, 삭제
- **팀 관리**: 팀 생성, 조회
- **N+1 문제 해결**: Batch Size, Query Projection 적용
- **페이징 조회**: Spring Data JPA의 Pageable 사용

## 기술 스택

- **Backend**: Spring Boot 3.1.6
- **Database**: H2 (In-Memory)
- **ORM**: JPA (Hibernate)
- **Build Tool**: Gradle
- **Language**: Java 17

## 설치 및 실행

### 사전 요구사항

- Java 17 이상
- Gradle (또는 Gradle Wrapper 사용)

### 실행 방법

1. **프로젝트 클론**:

   ```bash
   git clone https://github.com/daewook0401/JPAtoH2DataBase-test.git
   cd JPAtoH2DataBase-test/test
   ```

2. **빌드**:

   ```bash
   ./gradlew build
   ```

3. **실행**:

   ```bash
   ./gradlew bootRun
   ```

   또는 JAR 파일 실행:

   ```bash
   java -jar build/libs/test-0.0.1-SNAPSHOT.jar
   ```

4. **H2 콘솔 접속**:
   - URL: http://localhost:80/h2-console
   - JDBC URL: jdbc:h2:mem:testdb
   - Username: sa
   - Password: sa

## API 엔드포인트

### 멤버 API

- **GET /members**: 멤버 목록 조회 (페이징 지원)
  - 쿼리 파라미터: `page`, `size`
  - 예: `GET /members?page=0&size=10`

- **POST /members**: 멤버 생성
  - 바디: `RequestMemberDTO` (username, email, password, name, phoneNumber)

- **PUT /members/{id}**: 멤버 수정

- **DELETE /members/{id}**: 멤버 삭제

### 팀 API

- **GET /teams**: 팀 목록 조회

- **POST /teams**: 팀 생성

## 데이터 초기화

애플리케이션 시작 시 `DefaultInitializer`가 실행되어 다음과 같은 데이터를 자동 생성합니다:

- **팀**: 10개 팀 (개발팀, 마케팅팀, 운영팀 등)
- **멤버**: 각 팀당 100명씩 총 1000명

## N+1 문제 해결

프로젝트에서는 브랜치별로 다른 방법으로 N+1 문제를 해결합니다. 각 브랜치는 특정 해결법을 구현합니다.

### 1. **main 브랜치**: 기본 구현 (N+1 문제 발생)

- 기본 JPA 설정으로 N+1 문제가 발생하는 상태.
- 멤버 조회 시 team 정보를 lazy loading으로 가져옴.

### 2. **batch-size 브랜치**: Batch Size 적용

- Hibernate의 Batch Fetch를 활용하여 N+1 문제 해결.
- 설정 파일 (`application.yaml`):

  ```yaml
  jpa:
    hibernate:
      default_batch_fetch_size: 10
      batch_fetch_style: PADDED
  ```

- 엔티티에 `@Fetch(FetchMode.SELECT)` 추가로 SELECT 모드 강제.

### 4. **entity-graph 브랜치**: EntityGraph 적용

- JPA의 `@EntityGraph`를 사용하여 필요한 연관 관계를 미리 로딩.
- Repository 메소드에 `@EntityGraph` 어노테이션 적용.
- 예: `@EntityGraph(attributePaths = {"team"})`

### 3. **query_projection 브랜치**: Query Projection 적용 예정

- JPQL의 `new` 키워드로 DTO 직접 매핑.
- 엔티티 로딩 없이 필요한 데이터만 조회.
- 예: `SELECT new MemberProjectionDTO(m.id, m.username, t.teamName) FROM MemberEntity m JOIN m.team t`

## 테스트

```bash
./gradlew test
```

## 기여

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 라이선스

이 프로젝트는 MIT 라이선스를 따릅니다.
