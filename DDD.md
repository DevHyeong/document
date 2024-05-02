### 카카오 ML SaaS DDD 도입기
- https://tech.kakao.com/2022/12/12/ddd-of-recommender-team/


### 헥사고날 아키텍처(Feat. IMQA)
- 코드 응집성: 서로 유사한 역할을 하는 코드들은 같이 모아둬야 한다는 것
- 코드 의존성을 낮추고 관계있는 코드간의 응집성을 높여서 재사용성과 유지보수, 개발의 생산성을 높이는 주요한 5가지 원칙이 존재한다. 바로 할리우드 원칙과 SOLID원칙이다.
- 할리우드 원칙은 단 한 문장으로 설명이 된다.
  > 당신이 나를 부르는게 아니야, 내가 당신을 부르는 거지
- 육각형 아키텍처, 아래 2가지의 제약 조건을 지키게 코드를 작성해주면 된다.
  - 도메인 영역 -> 인프라 영역으로는 접근 가능하지만, 반대로는 불가능하게 구성해야 한다.
  - 무조건 포트, 어댑터를 통해서만 도메인 영역 -> 인프라 영역으로 접근하게 해야 한다.
- 육각형 아키텍처의 세부적 구조
  - 인바운드 어댑터
  - 아웃바운드 어댑터
  - 인바운드 포트
  - 아웃바운드 포트
  - 서비스    

- https://blog.imqa.io/hexagonal-architecture/


### 제로부터 시작하는 DDD를 위한 이벤트스토밍
- 도메인 주도 설계는 서비스의 "기능"을 기준으로 코드를 구분하지 않고, "도메인"이라는 비즈니스 영역을 기준으로 코드를 구분하는 것이 가장 핵심적이다. 이렇게 한다면 각 코드의 역할과 책임이 명확해지고 비즈니스 로직을 이해하고 이해하고 관리하는 것이 한결 더 쉬워지게 될 것이다.
- 이벤트 스토밍(Event Storming)은 서비스와 관계 있는 모든 이해관계자들이 서로가 가지고 있는 생각을 공유하며 서비스에서 발생하는 이벤트를 중심으로 분석하는 기법이다.
- 이벤트 스토밍은 보통 벽이나 대형 보드에 스티커를 붙이며 시작하게 된다. 이 스토커들은 서비스에서 발생하는 이벤트를 나타내며 각각의 색상은 서로 다른 의미를 가지게 된다. 이를 통해 시스템에서 어떤 행위가 발생하는지, 그 결과로 어떤 이벤트가 일어나는지를 명확하게 표현할 수 있게 된다.
- 이벤트 스토밍을 통해 우리는 다음과 같은 질문에 답을 할 수 있게 될 것이다.
  - 서비스에서 어떤 주체가 어떤 행동을 취하는가?
  - 그 행동의 결과로 어떤 이벤트가 발생하는가?
  - 이벤트가 발생하면 시스템에서는 어떤 변화가 일어나는가?
  - 이 이벤트가 다른 이벤트에 어떤 영향을 미치는가?
 
- 이벤트 스토밍의 구성요소
  - 도메인 이벤트
  - 커맨드
  - 액터
  - 정책
  - 외부시스템
  - 어그리게이트
  - 바운디드 컨텍스트
- 이벤트 스토밍의 과정 예시(블로그 참조)
  - 키워드 : 도메인 이벤트 도출, 이벤트에 따른 정책 도출, 커맨드와 액터 식별, 어그리게이트 매핑, 바운디드 컨텍스트, 컨텍스트 매핑    
- https://custom-li.tistory.com/207


### 이벤트 스토밍 수행방법
1. 발생 가능한 Event를 무작위로 도출하고, Policy, Command, Aggregate 순서로 이벤트를 중심으로 스티커별 해당 내용을 정의하고 발생시간 순서로 벽면에 부착한다.
2. Bounded Context를 설정하고 서브 도메인 간의 컨텍스트 매핑을 통해 Bounded Context간의 정보 참조의 릴레이션을 정의한다.
- 과정
  1) 이벤트 정의
     - 비즈니스 이벤트는 과거형으로 작성하는데 도메인 내부에 상태가 변화되고 난 결과가 이벤트이다.
  2) 정책 정의
     - 어떤 이벤트에 이어서 곧바로 항상 발생해야 하는 업무 규칙
     - 구현상에서는 이벤트의 Publish에 따라 벌어지는 이후의 프로세스가 자동으로 트리거 되게함
  3) 커맨드 도출
     - 이벤트를 발생시키는 행위(예: UI를 통해, 시간도래, 다른 이벤트에 의해)
     - 어떠한 상태의 변화를 일으키는 서비스
  4) 액터 정의
     - 액터는 커맨드를 발생시키는 주체(사람, 시스템 등)를 말한다.
  5) Aggregate 정의
     - 결합물을 의미
     - 어떤 도메인 객체를 중심으로 하나의 트랜잭션(ACID)에 묶여 변화되어야 할 객체의 묶을 도출한다.
  6) Bounded Context 도출
     - Bounded Context는 동일한 문맥으로 효율적으로 업무 용어를 사용할 수 있는 객체 범위를 뜻한다.
     - 하나의 Bounded Context는 하나 이상의 어그리게잇을 원소로 구성될 수 있다.
  7) Context 매핑
     - Bounded Context간 정보 참조 릴레이션 설정하는 작업을 말한다.
     - 컨텍스트간 매핑 정보만 보더라도 전체 도메인 서비스의 참조 토폴로지를 한 눈에 파악 가능하다.
       
- https://www.msaschool.io/operation/design/design-three/