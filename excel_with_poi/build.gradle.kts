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
    implementation ("com.github.sritejakv:splitwise:1.0")
    implementation ("com.splitwise:tokenautocomplete:4.0.0-beta05")
    implementation("org.apache.httpcomponents:httpclient:4.5.14")
    implementation("org.apache.httpcomponents:httpmime:4.5.14")
    implementation("com.itextpdf:itextpdf:5.5.13")
    implementation("com.itextpdf:cleanup:4.0.0")



//    implementation group: 'org.apache.poi', name: 'poi-ooxml', version: '5.2.3'
}

tasks.test {
    useJUnitPlatform()
}