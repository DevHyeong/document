
## api vs implementation



## annotationProcessor
- 컴파일 단계에서 annotation에 정의된 일렬의 프로세스를 동작하게 하는 것을 의미
- 컴파일 단계에서 실행되기 때문에 빌드 단계에서 에러를 출력하게 할 수 있고, 소스코드 및 바이트 코드를 생성할 수도 있다.
- 자바 컴파일러 플러그인의 일종으로 어노테이션에 대한 코드베이스를 검사, 수정, 생성하는 역할
- annotation Processor가 어노테이션을 기반으로 실제 코드를 검사, 수정, 생성하게 됨
- 기본적으로 포함되어 있는 어노테이션이 아니면 annotationProcessor를 통해 추가해야 한다.
- https://roadj.tistory.com/9
