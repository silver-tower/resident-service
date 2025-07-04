# 🏠 실버타운 입주민 서비스

## 📑 목차
- [📋 개요](#-개요)
- [🛠️ 개발 환경](#-개발-환경)
   - [기술 스택](#기술-스택)
- [📚 API 문서 생성 및 확인](#-api-문서-생성-및-확인)
   - [🚀 통합 개발 워크플로우](#-통합-개발-워크플로우-권장)
   - [⚡ API 명세(OpenAPI 3) 생성](#-api-명세openapi-3-생성)
   - [📖 Swagger-UI를 통한 문서 확인](#-swagger-ui를-통한-문서-확인)
- [🚀 시작하기](#-시작하기)

---

## 📋 개요

실버타운 입주민 서비스는 실버타운의 **회원**, **입주민**, **거주지** 정보를 관리하는 REST API 서비스입니다.

- **회원 관리**: 사용자 회원가입, 로그인, 프로필 관리
- **입주민 관리**: 실버타운 입주민 등록 및 정보 관리  
- **거주지 관리**: 거주지 배정, 변경, 조회 기능
- **API 문서**: OpenAPI 3 명세 + Swagger-UI를 통한 실시간 문서 확인

## 🛠️ 개발 환경

### 기술 스택
- **Java**: 17
- **Gradle**: 8.x
- **Spring Boot**: 3.5.0
- **Database**: MySQL 8.0
- **API 문서**: OpenAPI 3 + Swagger-UI

## 📚 API 문서 생성 및 확인

### 🚀 통합 개발 워크플로우 (권장)

#### 방법 1: 전체 워크플로우 실행
```bash
# 테스트 실행 → API 명세(OpenAPI 3) 생성 → 빌드 수행
./gradlew build
```
#### 방법 2: IntelliJ Gradle 탭에서
1. **Gradle 탭** 열기
2. **Tasks > build > build** 더블클릭

---

### ⚡ API 명세(OpenAPI 3) 생성
**API 명세 파일(OpenAPI 3)을 독립적으로 생성하려면 아래 명령어를 사용합니다.**
```bash
./gradlew openapi3
```

- 생성된 API 명세 파일 경로:
  ```
  build/openapi/openapi3.yaml
  ```

---

### 📖 Swagger-UI를 통한 문서 확인
API 문서는 **Swagger-UI**를 통해 손쉽게 확인할 수 있습니다.

#### Swagger-UI 접속 방법
1. 서버를 실행합니다:
   ```bash
   ./gradlew bootRun
   ```
2. 브라우저에서 Swagger-UI URL로 접속:
   ```
   http://localhost:8080/swagger-ui/index.html
   ```

#### OpenAPI 명세 파일 확인
Swagger-UI를 통해 API 명세를 확인하거나, 직접 YAML 파일을 확인할 수도 있습니다:
```
http://localhost:8080/openapi/openapi3.yaml
```

---

## 🚀 시작하기

1. **전체 빌드 및 서버 실행**
   ```bash
   ./gradlew bootRun
   ```

2. **API 문서 확인**
   Swagger-UI를 통해 다음 주소로 접속하세요:
   ```
   http://localhost:8080/swagger-ui/index.html
   ```
```