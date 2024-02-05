# Git


## git 정보 참고 사이트
https://www.lesstif.com/gitbook/

## git user 설정
```
git config --global user.name "jo"
git config --global user.email chojh3123@gmail.com
```


## commit한 Author 변경
```git
git commit -amend --author="chojh3123@gmail.com"
```

## commit한 Author 변경(원격 레포지토리로 이미 push한 경우)
```
git rebase -i HEAD~3 또는 git rebase -i <커밋hash값>
```
vi화면에서 변경할 커밋의 pick -> e로 변경후 :wq 를 눌러 저장
이후 아래 명령어 실행

```
git commit -amend --author="chojh3123@gmail.com"

git rebase --continue
git push -f origin master
```

커밋 hash값은 로그를 통해 확인할 수 있다.
```
git log
```

## 커밋 컨벤션 정의
- https://yozm.wishket.com/magazine/detail/1974/
- https://velog.io/@shin6403/Git-git-%EC%BB%A4%EB%B0%8B-%EC%BB%A8%EB%B2%A4%EC%85%98-%EC%84%A4%EC%A0%95%ED%95%98%EA%B8%B0

