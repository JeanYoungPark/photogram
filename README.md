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

###배운점
1. class로 응답할 때 <b>MessageConverter</b>가 자동으로 json으로 변경해서 응답해준다.
2. 스프링 시큐리티는 자동으로 지원해주는 것이 많다. (auth패키지 참고 + /logout 경로로 이동시 자동으로 세션을 끊어주기도 한다.)

### 데이터베이스

```sql
create user 'cos'@'%' identified by 'cos1234';
GRANT ALL PRIVILEGES ON *.* TO 'cos'@'%';
create database photogram;
```

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
    url: jdbc:mariadb://localhost:3306/costa?serverTimezone=Asia/Seoul
    username: costa
    password: costa1234
    
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

file:
  path: C:/src/springbootwork-sts/upload/
```

### 태그라이브러리

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
```
