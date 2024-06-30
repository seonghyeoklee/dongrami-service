# Dongrami

## 프로젝트 개요

```groovy
TODO : 프로젝트 개요 작성
```

### Tech Stack

#### Server

Java 17, Spring Boot 2.7, JPA, QueryDsl

#### Database

Mysql, H2

#### Monitoring

Spring Actuator, Prometheus, Grafana

#### Etc

Locust, Gradle, Docker

### 설치 및 실행 방법

소스 코드 클론

```bash
git clone [URL]
```

의존성 설치
```bash
cd [프로젝트 디렉토리]

./gradlew build
```

.env 파일 생성

```bash
MYSQL_HOST=localhost
MYSQL_PORT=3306
MYSQL_ROOT_PASSWORD={password}
MYSQL_DATABASE={database}
MYSQL_USER={user}
MYSQL_PASSWORD={password}
```

