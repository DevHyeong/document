// Spring Boot 3.x버전 기준

plugins {
    java
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "org.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("com.mysql:mysql-connector-j")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    //compileOnly("com.querydsl:querydsl-core")
    //compileOnly("com.querydsl:querydsl-jpa")
    implementation("com.querydsl:querydsl-jpa:${dependencyManagement.importedProperties["querydsl.version"]}:jakarta")
    annotationProcessor("com.querydsl:querydsl-apt:${dependencyManagement.importedProperties["querydsl.version"]}:jakarta")
    annotationProcessor("jakarta.persistence:jakarta.persistence-api")
    annotationProcessor("jakarta.annotation:jakarta.annotation-api")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.test {
    useJUnitPlatform()
}

// QueryDSL Build Options
val querydslDir = "$buildDir/generated/querydsl"

sourceSets {
    main{
        java {
            srcDirs += file(querydslDir)
        }
    }
}

// JavaComplie할때 querydsl QClass 파일 생성 위치 지정
// Gradle - Task - other - compileJava
tasks.withType<JavaCompile>{
    options.generatedSourceOutputDirectory = file(querydslDir)
}

// gradle clean시 QClass Directory 삭제
// Gradle - Task - build - clean
tasks.clean {
    doLast{
        file(querydslDir).deleteRecursively()
    }
}


/**
  참고자료
  - https://jojoldu.tistory.com/372
  - https://honeymon.io/tech/2020/07/09/gradle-annotation-processor-with-querydsl.html
  - https://myvelop.tistory.com/213
*/
