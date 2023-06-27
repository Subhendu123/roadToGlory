plugins {
	java
	war
	id("org.springframework.boot") version "3.1.0"
	id("io.spring.dependency-management") version "1.1.0"
}

group = "com.road2glory"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("org.apache.logging.log4j:log4j-core:2.20.0")
	implementation("org.springframework.boot:spring-boot-configuration-processor:3.1.1")
	implementation ("org.apache.poi:poi:5.2.3")
	implementation ("org.apache.poi:poi-ooxml:5.2.3")


}

tasks.withType<Test> {
	useJUnitPlatform()
}
