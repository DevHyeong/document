
# Kotlin으로 JPA Entity를 정의하는 방법
- 해당 글은 기술 블로그를 읽고 정리한 내용입니다. 

## 안티패턴
- 다음은 몇가지 안티패턴입니다.

### 1. 무분별한 mutable property 사용
- Field로 노출하든, Property로 노출하든 Entity가 가진 내부 상태의 변경을 직접 노출하도록 정의하는 것은 좋지 못하다.


### 2. Data class 활용
- 코틀린의 Data class는 데이터를 전달하기 위한 용도로 만들어진 클래스이다. 그래서 데이터를 전달하는 구조체는 사용자가 전달한 데이터의 원본을 유지하는 것이 중요하다.
- 하지만 Entity는 식별자 외에는 생명ㅇ주기동안 상태가 변경될 수 있다.
- 그리고 Entity는 특정 생명주기를 가지고 비즈니스 요구사항을 수행하는 객체이므로 단순히 데이터를 전달하기 위한 용도로 사용하는 Data class와는 성격이 다르다.

### 3. lateinit 사용
- 코틀린에서 lateinit은 초기화를 미루어 꼭 필요한 경우에 초기화를 해 성능향상 및 자원 효율성에 도움을 주려는 용도로 사용한다.
- 코틀린에서는 자바와 달리 초기화를 하지 않고 Property를 정의할 수 없다.
- lateinit 사용보다는 nullable한 변수를 선언하자
```kotlin
@Entity
class Board(
  title: String,
  writer: User,
){
  @Id
  var id: UUID = UUID.randomUUID()

  @Column
  var title: String = title

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  var writer: User = writer
}
```

## 도메인 정의 꿀팁

- 






## 레퍼런스
- https://spoqa.github.io/2022/08/16/kotlin-jpa-entity.html
