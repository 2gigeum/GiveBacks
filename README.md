# GiveBacks - 자원봉사 연결 플랫폼

## 개요

GiveBacks는 지역사회의 자원봉사를 촉진하고 봉사자들과 기관들 간의 연결을 원활하게 도와주는 온라인 플랫폼입니다. 
사용자들은 다양한 봉사 기회를 찾을 수 있으며, 봉사 후 경험을 공유하고 소통할 수 있는 커뮤니티를 제공합니다.

## 사용 기술

##### Back-End : JAVA 11 , Spring Boot , JPA

##### Front-End : Html , CSS , JavaScript 

##### Template Engine : Thymeleaf  

##### DataBase :  MySQL , Redis  

## 회원 관리
#### 회원가입
- [x] 회원가입 이메일 인증 (이메일 보내기)
- [x] 랜덤 문자열 6자리 이메일 토큰 생성 
- [x] 이메일로 발신된 이메일 토큰 Redis에 저장 (유효시간 1분)
- [x] 회원가입시 이메일 , 비밀번호 , 이름 ,닉네임 입력
- [x] 중복된 이메일시 JPA를 이용해 중복된 이메일 메세지 출력
- [x] 이메일 인증전 회원가입시 클릭시 이메일 인증을 먼저 완료하라는 메세지 출력

      
#### 로그인/ 로그아웃
- [x] 로그인시 사용자의 이메일의 세션을 저장.
- [x] 로그인후 세션값에 따라 메인페이지의 상단바 회원가입, 로그인 제거후 본인의 회원정보 출력
- [x] 로그아웃시 세션 초기화




### 동작하는 사이트 이미지
<img width="1280" alt="인덱스페이지" src="https://github.com/2gigeum/GiveBacks/assets/108059400/f16fc6af-1873-417c-8c04-8e37d7530f0a">
<img width="1259" alt="회원가입 페이지png" src="https://github.com/2gigeum/GiveBacks/assets/108059400/3479a2a9-65c2-4800-b052-8b7d95ad1e21">
<img width="1277" alt="로그인페이지" src="https://github.com/2gigeum/GiveBacks/assets/108059400/076a59c2-e263-42bb-bbb4-c64c2665251b">
<img width="1279" alt="로그인 후 메인페이지" src="https://github.com/2gigeum/GiveBacks/assets/108059400/23d3d087-fe88-4d51-bdc6-32bfe749bb49">




