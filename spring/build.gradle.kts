
// spring cloud 적용법
import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension

val springCloudVersion = "2023.0.1"

the<DependencyManagementExtension>().apply {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
    }
}

dependencies {
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
}

