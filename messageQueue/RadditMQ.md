# RadditMQ

## RabbitMQ란?
- RabbitMQ는 AMQP를 따르는 오픈소스 메시지 브로커
- 메시지를 많은 사용자에게 전달하거나, 요청에 대한 처리 시간이 길 때 해당 요청을 다른 API에게 위임하고 빠른 응답을 할 때 많이 사용한다.
- MQ를 사용하여 애플리케이션 간 결합도를 낮출 수 있는 장점도 있다.

## AMQP란?
- AMQP는 Advanced Message Queueing Protocol의 줄임말
- 클라이언트가 메시지 미들웨어 브로커와 통신할 수 있게 해주는 메시징 프로토콜

## 동작원리
- Producers -> [Exchange -> Binding -> Queue] -> Consumers
- 메시지를 발행하는 Producer에서 Broker(Exchange -> Biding -> Queue)의 Exchange로 메시지를 전달하면 Binding 이라는 규칙에 의해 연결된 Queue로 메시지가 복사된다.
- 메시지를 받아가는 Consumer에서는 브로커의 Queue를 통해 메시지를 받아가서 처리한다.
- AMQP에는 네트워크 문제나 메시지를 처리하지 못하는 경우를 대비해 2가지 수신 확인 모델을 갖추고 있다.
  1. Consumer는 메시지를 받으면 명시적으로 broker에게 통지하고, 브로커는 이 알림을 받았을 때만 Queue에서 메시지를 삭제한다.
  2. Broker가 메시지를 전달하면 자동으로 삭제하는 방식
- 모든 메시지는 Queue로 직접 전달되지 않고 반드시 Exchange에서 먼저 받는다. 그리고 Exchange Type과 Binding 규칙에 따라 적절한 Queue로 전달된다.
- 생성된 Exchange에는 전달받은 메시지를 원하는 Queue로 전달하기 위해 Bindings 이라는 규칙을 정의할 수 있다.
- 

## Exchange Type
### Direct Exchange
### Topic Exchange
### Headers Exchange
### Fanout Exchange




## 참고
- https://somaz.tistory.com/119
- https://jonnung.dev/rabbitmq/2019/02/06/about-amqp-implementtation-of-rabbitmq/
