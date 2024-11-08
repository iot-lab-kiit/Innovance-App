plugins {
	java
	id("org.springframework.boot") version "3.3.5"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "in.iot.lab"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
	maven { url = uri("https://plugins.gradle.org/m2/") }
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	compileOnly("org.projectlombok:lombok")
	runtimeOnly("com.mysql:mysql-connector-j")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	// OkHttp
	implementation("com.squareup.okhttp3:okhttp:4.12.0")
	// WebFlux
	implementation("org.springframework.boot:spring-boot-starter-webflux:3.3.5")
	//Postgres
	implementation ("org.postgresql:postgresql:42.6.0")
	// Redis
	implementation("org.springframework.boot:spring-boot-starter-data-redis:3.3.5")
	// Redis Client
	implementation("io.lettuce:lettuce-core:6.5.0.RELEASE")


}

tasks.withType<Test> {
	useJUnitPlatform()
}
