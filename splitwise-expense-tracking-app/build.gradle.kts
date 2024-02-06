plugins {
    java
    war
    id("org.springframework.boot") version "3.1.0"
    id("io.spring.dependency-management") version "1.1.0"
}

group = "com.road2glory"
version = "2.0"
//bootWar {
//    archiveFileName = archiveFileName("service.war")
//}

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
//	flatDir {
//		dirs("C:\\Workspaces\\roadToGlory\\excel_with_poi\\build\\classes")
//	}
}


dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
    implementation("org.springframework.boot:spring-boot-starter-websocket") {
        exclude("spring-boot-starter-tomcat")
    }
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.apache.logging.log4j:log4j-core:2.20.0")
    implementation("org.springframework.boot:spring-boot-configuration-processor:3.1.1")
    // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")

    implementation("org.apache.poi:poi:5.2.3")
//	implementation (project(path = ":excel_with_poi"))
//	implementation fileTree("")
    implementation("org.apache.poi:poi-ooxml:5.2.3")
//	implementation (project(":excel_with_poi"))
//	runtimeOnly(project(":excel_with_poi"))

}
configurations.implementation {
    exclude(group = "org.apache.logging.log4j", module = "log4j-to-slf4j")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
tasks.war {
//    archiveFileName.set("splitwise-app-" + version + ".war")
    archiveFileName.set("splitwise-app.war")

}

