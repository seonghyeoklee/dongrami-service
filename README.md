# Dongrami

## 프로젝트 개요


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

### 기능 정의

#### User

- [ ] 회원가입
- [ ] 로그인
- [ ] 로그아웃
- [ ] 회원정보 수정
- [ ] 회원탈퇴
- [ ] 비밀번호 찾기
- [ ] 비밀번호 변경

#### Todo

- [X] Todo 생성
- [X] Todo 수정
- [X] Todo 삭제
- [X] Todo 전체 조회 (페이징)
- [X] Todo 상세 조회
- [X] Todo 상태 변경
- [X] Todo 핀셋 설정/해제
- [X] Todo 내일하기
  - default : 조회된 Todo 내용 다음날로 복사
- [X] Todo 기억해두기

#### Diary

- [ ] Diary 생성
- [ ] Diary 수정
- [ ] Diary 삭제
- [X] Diary 전체 조회 (페이징)
- [ ] Diary 상세 조회
- [ ] Diary 공유
- [ ] Diary 공유 해제
- [ ] Diary 공유 목록 조회
- [ ] Diary 공유 알림 설정
- [ ] Diary 공유 알림 해제

#### File

- [ ] File 업로드
- [ ] File 다운로드
- [ ] File 삭제
- [ ] File 썸네일 생성

#### Notification Center

- [ ] 알림 전체 조회 (페이징)
- [ ] 알림 상세 조회
- [ ] 알림 삭제
- [ ] 알림 읽음 처리
- [ ] 알림 전체 읽음 처리
 
#### Calendar

- [ ] Todo, Diary 일정 조회

