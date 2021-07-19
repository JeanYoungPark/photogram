# 포토그램 - 인스타그램 클론 코딩

[comment]: <> (### STS 툴에 세팅하기 - 플러그인 설정)

[comment]: <> (- https://blog.naver.com/getinthere/222322821611)

### 의존성

- Sring Boot DevTools
- Lombok
- Validation
- Spring Data JPA
- MariaDB Driver
- Spring Security
- Spring Web
- oauth2-client

###구현기능
- 회원가입
- 로그인 ,페이스북 로그인
- 회원정보 수정
- 구독 및 구독취소
- 이미지 업로드
- 스토리 리스트
- 좋아요 및 좋아요취소
- 댓글달기 및 댓글삭제


###배운점
1. class로 응답할 때 <b>MessageConverter</b>가 자동으로 json으로 변경해서 응답해준다.
2. N:N의 관계는 중간테이블이 필요하다.
3. 이미지 폴더를 외부에 두는 이유는 내부에 두었을때 이미지를 업로드 하면 deploy해야하는데 그 시간보다 후 처리 예를 들어 리다이렉트 되는 속도가 더 빠르면 오류와 같이 보일 수 있긴 때문, 하지만 외부에 두었을때는 deploy를 하지 않기때문에 업로드 이후 후 처리가 진행된다.
4. qlrm 데이터베이스 결과를 자바클래스와 매핑해주는 라이브러리이다.

### yml 설정

```yml
server:
  port: 8080
  servlet:
    context-path: /
    encoding:
      charset: utf-8
      enabled: true
    
spring:
  mvc:
    view:
      prefix: /WEB-INF/views/
      suffix: .jsp
      
  datasource:
    driver-class-name: org.mariadb.jdbc.Driver
    url: jdbc:mariadb://localhost:3306/(스키마이름))?serverTimezone=Asia/Seoul
    username: (아이디)
    password: (비밀번호)
    
  jpa:
    open-in-view: true
    hibernate:
      ddl-auto: update
      naming:
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
    show-sql: true
      
  servlet:
    multipart:
      enabled: true
      max-file-size: 2MB

  security:
    user:
      name: test
      password: 1234   

    oauth2:
      client:
        registration:
          facebook:
              client-id: (아이디)
              client-secret: (비밀키)
              scope:
                - public_profile
                - email

file:
  path: C:/src/springbootwork-sts/upload/
```
