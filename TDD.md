# TDD에 대한 내용들


### Mock 객체 남용은 테스트 코드를 망친다
  - https://medium.com/@chanhyeonglee/mock-%EA%B0%9D%EC%B2%B4-%EB%82%A8%EC%9A%A9%EC%9D%80-%ED%85%8C%EC%8A%A4%ED%8A%B8-%EC%BD%94%EB%93%9C%EB%A5%BC-%EB%A7%9D%EC%B9%9C%EB%8B%A4-f38129e5d40a

### 성능 테스트
  - https://techblog.woowahan.com/2572/

### 테스트 주도 시작하기 개발하기(저자 최범균님) 요약본
  - https://incheol-jung.gitbook.io/docs/study/undefined-3/chap-02.-tdd

### TDD 개발을 하면서 들었던 생각들
  - 작성된 테스트케이스가 정말 신뢰할 수 있는가?
    - 코드 리펙토링을 하면서 기존 코드가 TDD 개발을 하지 않았던터라 테스트 케이스를 만들면서 리펙토링을 진행하였다. 유틸 클래스(리펙토링할 대상)의 메소드 단위로 리펙토링과 테스트 케이스를 작성하면서 정말 이 테스트를 통과하면 기능에 문제가 없이 리펙토링이 잘된건가 하는 의문이 들었다. 
  - 왜 신뢰가 되지 않는가?
    - TDD 개발이 익숙지 않아서 그런것같다.
  - 해당 테스트 케이스의 관심사는 한가지여야 한다.(메소드 단위)
    - 테스트의 검증대상이 무엇인가? SQL 쿼리를 통해 가져온 데이터를 자바에서 제대로 파싱을 하고 있는지에 대한 테스트가 필요했다. 
    - 하나의 테스트 케이스에서 검증 대상이 두가지 이상(쿼리결과값, 데이터 파싱)은 부적합하고, 원하는 검증대상은 자바에서 제대로 파싱하고 있는지에 대한 테스트였기 때문에 SQL 쿼리결과는 데이터베이스에 의존하는 코드를 만들지 않기 위해서 HashMap으로 관리하도록 하였다.

### TDD
- https://jojoldu.tistory.com/674
- https://jwchung.github.io/testing-oh-my
- https://www.youtube.com/watch?v=YdtknE_yPk4

### mockMvc model test시 구조(통합테스트)
- 웹 페이지를 불러오는 방식 중에 SSR(Servier Side Rendering)은 spring의 Model에 데이터를 담아 view에 넘기게 된다.
- 이 때 Model에 담긴 객체(json형태)의 값을 테스트하고 싶을 때 아래의 코드를 사용하면 된다.
```
  MvcResult mvcResult = mockMvc.perform(get("url path")
			.servletPath("url path")
			.sessionAttr("session", session))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("model명"))
			.andReturn();
		
  Map<String, Object> map = mvcResult.getModelAndView().getModel();
  Object object = map.get("model명");
  A a = new ObjectMapper().convertValue(object, A.class);
  
```

### spring legacy proect에 테스트시 h2적용
- ORACLE 함수적용 https://github.com/h2database/h2database/issues/1161 
- JPA가 아닌 mybatis로 특정 데이터베이스에 의존적인 쿼리를 만들었을 경우 h2적용시 지원하지 않는 문법이 존재하여 힘든 경우가 있다.

### 테스트 코드에서 내부 구현 검증 피하기
- 비지니스로직 안에서 일어나는 내부 구현에 대한 검증은 피하고 검증 단위는 비지니스 기능 단위로 하는 것이 좋다. 
- 이유는 비지니스로직 안에 리팩토링이 일어날 경우 내부구현을 검증하기 위한 테스트 코드를 수정할수밖에 없기 때문
- 내부 구현예시: private으로 선언된 함수나 메소드, repository에 대한 검증 등
- https://jojoldu.tistory.com/614

### 테스트 커버리지
- https://velog.io/@lxxjn0/%EC%BD%94%EB%93%9C-%EB%B6%84%EC%84%9D-%EB%8F%84%EA%B5%AC-%EC%A0%81%EC%9A%A9%EA%B8%B0-1%ED%8E%B8-%EC%BD%94%EB%93%9C-%EC%BB%A4%EB%B2%84%EB%A6%AC%EC%A7%80Code-Coverage%EA%B0%80-%EB%AD%94%EA%B0%80%EC%9A%94
- https://www.youtube.com/watch?v=jdlBu2vFv58

### FIRST 원칙
- 좋은 테스트 코드는 다음과 같은 특징을 가진다.
	- Fast : 테스트는 빠르게 동작하여 자주 돌릴 수 있어야 한다.
	- Independent : 각각의 테스트는 독립적이며 서로 의존해서는 안된다
	- Repeatable : 어느 환경에서도 반복 가능해야 한다.
	- Self-Validating : 테스트는 성공 또는 실패로 bool 값으로 결과를 내어 자체적으로 검증되어야 한다.
	- Timely : 테스트는 적시에 즉 테스트하려는 실제 코드를 구현하기 직전에 구현해야 한다.

### Spring Boot JPA, h2 테스트를 위한 설정(yml)
```yaml
spring:
  datasource:
    url: jdbc:h2:mem:devHyeong;MODE=MySQL;
    driver-class-name: org.h2.Driver
    username: sa
    password:
  sql:
    init:
      # schema-locations: classpath*:h2/schema.sql
      data-locations: classpath*:h2/data.sql
  jpa:
    hibernate:
      ddl-auto: create-drop
    properties:
      hibernate:
        show_sql: true
        format_sql: true
    defer-datasource-initialization: true # 데이터 초기화
    database-platform: org.hibernate.dialect.H2Dialect

  h2:
    console:
      enabled: true
      settings:
        web-allow-others: true
      path: /h2-console

```

- https://techblog.woowahan.com/14874/


