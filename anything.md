

## JdbcTemplate not configured when using @DataJpaTest
- https://stackoverflow.com/questions/39264354/jdbctemplate-not-configured-when-using-datajpatest


## 리스트 순회중 요소 삭제시 마주하는 ConcurrentModificationException

- https://codechacha.com/ko/java-concurrentmodificationexception/

## @Transactional(rollbackFor=Exception.class)을 설정했는데 왜 Exception발생시 롤백이 안될까?
- try ~ catch를 통해 Exception 처리를 하고 있는건 아닌지 확인
- throw를 통해 해당 exception을 던져야 한다. 그래야 spring 컨테이너가 exception 발생함을 알고 trasactional을 실행한다.
- 스프링 트랜잭션은 default로 언체크예외만 롤백한다. 체크예외는 위와같이 rollbackFor을 설정해야 한다.

## 객체지향적 개발은 어떻게 훈련해야 할까?
- https://www.slipp.net/questions/475
- https://codesquad-yoda.medium.com/%ED%9A%A8%EA%B3%BC%EC%A0%81%EC%9C%BC%EB%A1%9C-tdd-%EB%A6%AC%ED%8C%A9%ED%86%A0%EB%A7%81-oop%EB%A5%BC-%EC%97%B0%EC%8A%B5%ED%95%98%EB%8A%94-%EB%B0%A9%EB%B2%95%EC%9D%80-7ecc9ddb5d45

## 이력서 작성 꿀팁
https://wonny.space/writing/work/engineer-resume


## 리펙토링 중 고민들
- 같은 로직안에서 어떤 경우에는 a.merge를 실행시켜야하고, 어떤 경우에는 a.sum을 실행시켜야 하는 경우
  ```
  A b = new A();
  for(A a : list){
    if(a.equals(b){
      a.merge(b); // or a.sum(b);
    }
  }
  ```

## 개발을 하다가 막혔을 때(로직말고 기술적인 부분)
- 어떻게 구현해야할지 한창 고민할 때는 고민하고 있는 것에 대한 핵심이 무엇인지 다시 생각해보고, 그에 맞게 검색을 하면 올바른 코드를 만들기 위한 단서들을 찾을수있다. 그 단서를 통해서 이렇게 구현하면 되겠다 라는 생각이 들면 자연스럽게 원하던 결과가 도출된다.

## 책추천
https://yozm.wishket.com/magazine/detail/1892/

## 코드에 대한 분석
- 로직(메서드 기준)이 어떤 내용을 담고있는지 간단하고 명확하게 정리하여 기술
- 해당 로직은 테스트 코드를 작성하는데 용이한가에 대한 생각

## DDD 애그릿게이트
- https://medium.com/@SlackBeck/%EC%95%A0%EA%B7%B8%EB%A6%AC%EA%B2%8C%EC%9E%87-%ED%95%98%EB%82%98%EC%97%90-%EB%A6%AC%ED%8C%8C%EC%A7%80%ED%86%A0%EB%A6%AC-%ED%95%98%EB%82%98-f97a69662f63


## DAO vs Repository 차이점
- https://bperhaps.tistory.com/entry/Repository%EC%99%80-Dao%EC%9D%98-%EC%B0%A8%EC%9D%B4%EC%A0%90

## 사용자 행동로그
- https://medium.com/@connect2yh/%EC%82%AC%EC%9A%A9%EC%9E%90-%ED%96%89%EB%8F%99-%EB%A1%9C%EA%B7%B8-%ED%8C%8C%EC%A2%85%EB%B6%80%ED%84%B0-%EC%88%98%ED%99%95%EA%B9%8C%EC%A7%80-%EC%9E%98-%EC%8C%93%EA%B8%B0-1%ED%8E%B8-9733422dd00d


## IaaS, PaaS, SaaS 의미와 차이점
- https://blog.naver.com/gitple/221744064248

## 사용자 정의 예외는 꼭 정의해야될까? (표준예외로 대체할 수 없을까?)
- https://tecoble.techcourse.co.kr/post/2020-08-17-custom-exception/




## LocalDateTime 활용(Mybatis, application/json)

### 문제 상황

- varchar2 값으로 저장된 dateTime 값을 읽어와 Java 8의 LocalDateTime 타입의 변수에 담아야 하는 상황
- LocalDateTime 타입의 변수에 저장된 데이터를 json 형태로 API 통신이 되어야 하는 상황

### 해결

- TypeHandler 정의 (BaseTypeHanlder를 상속받아 구현체 정의) → **변수에 제대로 담겨짐(해결)**
- jackson-datatype-jsr310 의존성 추가 후 아래와 같이 코드 정의 → **json 형태로 API 통신 이루어짐(해결)**

```java
@JsonSerialize(using = LocalDateTimeSerializer.class)
@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMddHHmm", timezone = "Asia/Seoul")
private LocalDateTime anncStartDt;
```

### **하지만 LocalDateTime 타입으로 정의된 모든 변수에 위와 같이 정의하는 것이 비효율적**

- Spring은 application/json 형태로 반환하기 위해 MessageConverter 중 `MappingJackson2HttpMessageConverter` 를 사용, 이를 재정의
    - `objectMapper`의 **`Jackson2ObjectMapperFactoryBean`** 에 LocalDateTimeSerializer를 추가.
    - `MappingJackson2HttpMessageConverter` 가 재정의된 `objectMapper`를 사용하도록 메서드 주입



## 100만개 이상의 더미 데이터 만들기(Feat. EasyRandom)
- 예를 들어 아래와 같은 엔티티의 데이터를 100만개 만들어보자.
```java
@Entity
@Table(name = "orders")
@Getter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Orderer orderer;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private int totalAmount;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderProduct> orderProducts = new ArrayList<>();
    private LocalDateTime createdAt;
}
```








https://github.com/j-easy/easy-random






