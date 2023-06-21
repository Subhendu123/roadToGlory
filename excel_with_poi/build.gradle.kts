plugins {
    id("java")
}

group = "com.roadtoglory.excelwithpoi"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation ("org.apache.poi:poi:5.2.3")
    implementation ("org.apache.poi:poi-ooxml:5.2.3")

//    implementation group: 'org.apache.poi', name: 'poi-ooxml', version: '5.2.3'
}

tasks.test {
    useJUnitPlatform()
}