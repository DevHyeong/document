


## 도커 이미지
- 도커 이미지는 컨테이너를 만들기 위해 필요한 설정이나 종속성들을 갖고있는 소프트웨어 패키지
- 도커 이미지는 dockerhub을 통해 다른 사람들이 만들어 놓은 이미지를 사용할 수 있고 
- 도커 이미지를 이용해서 도커 컨테이너를 사용
```docker
  docker create 이미지 이름
```

## 도커 이미지 생성하는 순서
- Dockerfile 작성 -> 도커 클라이언트 -> 도커 서버 -> 이미지 생성
- 도커 파일에 입력된 것들이 도커 클라이언트에 전달되어서 도커 서버가 인식하게 하여야 한다. ( docker build ./ 또는 docker build . )
- Build 명령어는 해당 디렉토리 내에서 Dockerfile이라는 파일을 찾아서 도커 클라이언트에 전달시켜준다.
- docker build 뒤에 ./ 와 .는 둘다 현재 디렉토리를 가르킨다.
- 베이스 이미지에서 다른 종속성이나 새로운 커맨드를 추가할 때는 임시 컨테이너를 만든 후 그 컨테이너를 토대로 새로운 이미지를 만든다. 그리고 그 임시 컨테이너는 지워준다.

## Dockerfile
- Docker Image를 만들기 위한 설정파일
- 컨테이너가 어떻게 행동해야 하는지에 대한 설정들을 정의해줌
- 도커 이미지는 여러개의 레이어로 되어 있다. 그 중에서 베이스 이미지는 이 이미지의 기반이 되는 부분이다.
- 레이어는 중간 단계의 이미지

### 도커 파일 만드는 순서
1. 베이스 이미지를 명시(파일 스냅샷에 해당)
2. 추가적으로 필요한 파일을 다운받기 위한 몇가지 명령어를 명시해준다. (파일 스냅샷에 해당)
3. 컨테이너 시작시 실행될 명령어를 명시해준다.


```docker
# 베이스 이미지 명시
# 형식: <이미지 이름>:<태그>
# 태그가 없으면 가장 최신 버전을 다운받게 됨
FROM baseImage 

RUN command

# 컨테이너가 시작되었을 때 실행 파일 또는 쉘 스크립트
# DockerFile 내 1회만 사용 가능
CMD  ["echo", "hello"]
```


## Health check




## 참고
- 
- https://jaeseo0519.tistory.com/264

How to dockerize Spring Boot + React apps
- https://luizcostatech.medium.com/how-to-dockerize-spring-boot-react-apps-1a4aea1acc44
