

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
