# 6장 AOP
- AOP는 IoC/DI, 서비스 추상화와 더불어 스프링의 3대 기반기술의 하나다.
- 스프링에 적용된 가장 인기 있는 AOP의 적용 대상은 바로 선언적 트랜잭션 기능이다.
- 서비스 추상화를 통해 많은 근본적인 문제를 해결했던 트랜잭션 경계설정 기능을 AOP를 이용해 더욱 세련되고 깔끔한 방식으로 바꿔보자.
- 그리고 그 과정에서 스프링이 AOP를 도입해야 했던 이유도 알아보자.

## 트랜잭션 코드의 분리
- 아래는 UserService 클래스의 코드 일부이다.
```java
public void upgradeLevels() {
		TransactionStatus status = 
		  this.transactionManager.getTransaction(new DefaultTransactionDefinition());
		try {
			List<User> users = userDao.getAll();
			for (User user : users) {
				if (canUpgradeLevel(user)) {
					upgradeLevel(user);
				}
			}
			this.transactionManager.commit(status);
		} catch (RuntimeException e) {
			this.transactionManager.rollback(status);
			throw e;
		}
}
```

- 트랜잭션 경계설정 코드와 비즈니스 로직 코드로 두 가지 종류의 코드가 구분되어 있음을 알 수 있다.
- 이 두 가지 코드는 성격이 다를 뿐 아니라 서로 주고받는 것도 없는 완벽하게 독립적인 코드다.

### DI를 이용한 클래스의 분리
- DI의 기본 아이디어는 실제 사용할 오브젝트의 클래스 정체는 감춘 채 인터페이스를 통해 간접으로 접근하는 것이다. 그 덕분에 구현 클래스는 얼마든지 외부에서 변경할 수 있다.
- UserService를 인터페이스로 변경
- UserService 인터페이스의 구현 클래스 2개 정의
  - UserServiceImpl : 비즈니스 로직을 담당하는 클래스
  - UserServiceTx : 트랜잭션 경계설정을 담당하는 클래스
- UserServiceTx는 스스로는 비즈니스 로직을 담고 있지 않기 때문에 비즈니스 로직을 담고 있는 UserServiceImpl에 실제적인 로직 처리 작업은 위임하는 것이다.

### 트랜잭션 경계설정 코드 분리의 장점
- 비즈니스 로직을 담당하고 있는 UserServiceImpl의 코드를 작성할 때는 트랜잭션과 같은 기술적인 내용에는 전혀 신경 쓰지 않아도 된다.
- 트랜잭션은 DI를 이용해 UserServiceTx와 같은 트랜잭셕 기능을 가진 오브젝트가 먼저 실행되도록 만들기만 하면 된다.
- 비즈니스 로직에 대한 테스트를 손쉽게 만들어낼 수 있다.

## 고립된 단위 테스트
- 가장 편하고 좋은 테스트 방법은 가능한 한 작은 단위로 쪼개서 테스트하는 것이다. (디버깅이 쉬워짐)
- 하지만 작은 단위로 테스트하고 싶어도 그럴수 없는 경우가 많다.
- 테스트 대상이 다른 오브젝트와 환경에 의존한다면 작은 단위의 테스트가 주는 장점을 얻기 힘들다.

### 테스트 대상 오브젝트 고립시키기
- 테스트의 대상이 환경이나 외부 서버, 다른 클래스의 코드에 종속되고 영향을 받지 않도록 고립시킬 필요가 있다.
- 
