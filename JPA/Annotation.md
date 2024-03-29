### @Transient
- 칼럼을 제외하기 위해 사용한다. (영속 대상에서 제외시키기 위해 사용된다)
- 메서드나 필드에 선언할 수 있는 어노테이션
- 메서드에 선언할 때 영속 대상에서 제외되지 않는 경우
  - JPA 스펙에 따르면 JPA는 두 가지 방식을 통해 영속 상태인 엔티티 객체의 데이터에 접근할 수 있다. (프로퍼티 방식, 필드방식)
  - 필드 레벨에 선언시킬 수 있는 모든 JPA 어노테이션들은 기본적으로 프로퍼티 방식을 지원하기 위해 메서드 레벨을 지원하고 있다.
  - JPA는 엔티티 객체의 접근 방식이 다르게 결정된다. 기본적으로 엔티티 매니저의 1차 캐시는 Map<@Id, @Entity> 형태로 설계되어 key에 해당하는 @Id와 value에 해당하는 엔티티 객체를 저장하여 관리하게 된다.
  - 결과적으로 JPA의 엔티티 접근 방식은 @Id 어노테이션 위치에 의해 결정되며, 엔티티의 모든 필드 또는 상속된 엔티티의 계층에 대해서도 일관성 있게 적용해줘야 한다.
  - 따라서 @Id 어노테이션의 위치가 필드에 있을 경우 필드 접근 방식을 따르므로 메서드에 선언된 경우에는 영속 대상에서 제외되지 않는 문제가 발생한다.



