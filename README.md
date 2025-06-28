# 🏠 실버타운 입주민 서비스

## 📑 목차
- [📋 개요](#-개요)
- [🛠️ 개발 환경](#️-개발-환경)
   - [기술 스택](#기술-스택)
- [📚 API 문서 생성 및 확인](#-api-문서-생성-및-확인)
   - [🚀 통합 개발 워크플로우](#-통합-개발-워크플로우-권장)
   - [⚡ 개발 중 빠른 문서 업데이트](#-개발-중-빠른-문서-업데이트)
   - [📖 API 문서 확인](#-api-문서-확인)
- [🏗️ Gradle 태스크 구성](#️-gradle-태스크-구성)
   - [Application](#application)
   - [Documentation](#documentation)
   - [Build](#build)
- [🔍 문제 해결](#-문제-해결)
- [🚀 시작하기](#-시작하기)

---

## 📋 개요

실버타운 입주민 서비스는 실버타운의 **회원**, **입주민**, **거주지** 정보를 관리하는 REST API 서비스입니다.

- **회원 관리**: 사용자 회원가입, 로그인, 프로필 관리
- **입주민 관리**: 실버타운 입주민 등록 및 정보 관리  
- **거주지 관리**: 거주지 배정, 변경, 조회 기능
- **API 문서**: Spring REST Docs를 사용한 테스트 기반 자동 문서화

## 🛠️ 개발 환경

### 기술 스택
- **Java**: 17
- **Gradle**: 8.x
- **Spring Boot**: 3.5.0
- **Database**: MySQL 8.0
- **API 문서**: Spring REST Docs + Asciidoctor

## 📚 API 문서 생성 및 확인

### 🚀 통합 개발 워크플로우 (권장)

#### 방법 1: 전체 워크플로우 한 번에
```bash
# 테스트 → REST Docs 생성 → 빌드 실행을 한 번에
./gradlew build
```
#### 방법 2: IntelliJ Gradle 탭에서
1. **Gradle 탭** 열기
2. **Tasks > build > build** 더블클릭

### ⚡ 개발 중 빠른 문서 업데이트
```bash
# API 문서만 빠르게 업데이트
./gradlew asciidoctor
```
### 📖 API 문서 확인

서버 실행 후 브라우저에서 접속:
```
http://localhost:8080/docs/index.html
```
---

### API 개발 시
1. **요구사항 분석 및 API 설계**
2. **API 코드 작성**
3. **테스트 코드 작성** (REST Docs 포함)
4. **문서 생성**: `./gradlew asciidoctor` 실행
5. **브라우저에서 문서 확인**
6. **반복 작업 및 검증**

---

## 🏗️ Gradle 태스크 구성

### Application
- **`bootRun`**: 애플리케이션 실행 (문서 포함)
- **`test`**: 테스트 실행 및 스니펫 생성

### Documentation  
- **`asciidoctor`**: Asciidoctor 사용해 문서를 생성 (HTML 변환)
- **`copyDocument`**: 문서를 정적 리소스(`static/docs`)로 복사

### Build
- **`build`**: 일반 빌드 (문서 포함)

---

## 🔍 문제 해결

### Gradle 태스크가 동작하지 않는 경우
1. **View > Tool Windows > Gradle** 선택
2. Gradle **새로고침** 버튼 클릭 후 재시도

### 문서가 생성되지 않는 경우
1. 테스트 확인:
   ```bash
   ./gradlew test
   ```
2. 테스트가 성공한 경우 다음 명령 실행:
   ```bash
   ./gradlew asciidoctor
   ```

### 서버 실행 시 문서가 반영되지 않을 경우
```bash
# 빌드 후 실행
./gradlew build bootRun
```
---

## 🚀 시작하기

1. **전체 빌드 및 서버 실행**
   ```bash
   ./gradlew bootRun
   ```

2. **API 문서 확인**
   브라우저를 열어서 다음 주소로 접속하세요:
   ```
   http://localhost:8080/docs/index.html
   ```

