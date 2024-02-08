
최근 마이크로서비스 관련 책을 읽으면서 해당 git리포지토리에 저장된 소스를 보며 잊고 있었던 jpa 관련 내용을 정리해보려 한다.

## 값 타입 컬렉션(Feat. @ElementCollection, @CollectionTable)

JPA 데이터 타입은 엔티티 타입과 값 타입으로 분류할 수 있다.

값 타입의 분류
- 기본값 타입: primitive type, reference type, String
- 임베디드 타입(복합 값 타입)
- 값 타입 컬렉션: 값 타입을 여러 개 저장하고자 할때 사용하며, 자바의 컬렉션을 사용한다.

값 타입의 생명주기는 엔티티와 같은 생명주기를 따른다. 그렇기 때문에 컬렉션에 단순히 값을 추가하거나 삭제하기만 하면 데이터베이스에 반영된다. (일대다 관계에서 cascade = ALL, orphanRemoval = true 설정과 동일)

데이터 수명주기 동일시 값 타입 사용? 

### 예시
Order.java
```java
@Entity
@Table(name = "orders")
@Getter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    @ElementCollection
    @CollectionTable(name = "order_line_items")
    private List<OrderLineItem> orderLineItems;
    private Long consumerId;
    private Long restaurantId;
}
```
OrderLineItems.java
```java
@Embeddable
public class OrderLineItems {

  @ElementCollection
  @CollectionTable(name = "order_line_items")
  private List<OrderLineItem> lineItems;

}
```

### 값 타입 컬렉션의 제약
- 값 타입은 엔티티와 다르게 식별자 개념이 없기 때문에 값을 변경하면 추적이 어렵다.
- 값 타입 컬렉션에 변경 사항(저장, 삭제)이 발생하면 소유하는 엔티티와 연관된 모든 데이터를 삭제하고 현재 남아있는 값을 다시 저장한다.
- 

### 값 타입 컬렉션의 대안
- 값 타입 컬렉션 대신 일대다 관계를 고려
- 

### 참고
- https://peppydays.medium.com/jpa-%EC%95%84%EB%91%A5%EB%B0%94%EB%91%A5-elementcollection-%EA%B3%BC-%EC%A4%91%EC%B2%A9%EB%90%9C-embedded-%EA%B7%B8%EB%A6%AC%EA%B3%A0-accesstype-%EC%9D%B4%EC%8A%88-239a86b4d0ca

