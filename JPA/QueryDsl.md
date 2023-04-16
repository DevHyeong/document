# Querydsl

본 내용은 책과 여러 블로그를 통해 정리된 내용입니다.


## Querydsl이란?
JPQL을 편하게 작성하도록 도와주는 빌더 클래스 모음. 비표준 오픈소스 프레임워크

### 장점
가장 큰 장점은 아래와 같다.
- 문자가 아닌 코드로 쿼리를 작성함으로써, 컴파일 시점에 문법 오류를 쉽게 확인할 수 있다.
- 자동 완성 등 IDE의 도움을 받을 수 있다.

## 설정
필요한 라이브러리 : querydsl-jpa, querydsl-apt
주의점 : 사용하고 있는 spring-data-jpa에서 사용하는 

build.gradle

```
buildscript {
	ext {
		queryDslVersion = "5.0.0"
	}
}

plugins {
	id 'java'
	id 'org.springframework.boot' version '2.7.7'
	id 'io.spring.dependency-management' version '1.0.15.RELEASE'
}

group = 'kr.15gg'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '11'

repositories {
	mavenCentral()
}

dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	implementation group: 'com.querydsl', name: 'querydsl-jpa', version: queryDslVersion
	implementation group: 'com.querydsl', name: 'querydsl-apt', version: queryDslVersion
	
  annotationProcessor 'com.querydsl:querydsl-apt:${queryDslVersion}:jpa'
	annotationProcessor "javax.persistence:javax.persistence-api"
	annotationProcessor "javax.annotation:javax.annotation-api"

	testImplementation 'org.springframework.boot:spring-boot-starter-test'

}

sourceSets {
	main {
		java {
			srcDirs = ["$projectDir/src/main/java", "$projectDir/build/generated"]
		}
	}
}
```

QuerydslConfig.java
```
@Configuration
public class QuerydslConfig {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}
```

## 사용법
fetchJoin() multiple join시는 지원안함






## 참조
https://tecoble.techcourse.co.kr/post/2021-08-08-basic-querydsl/
