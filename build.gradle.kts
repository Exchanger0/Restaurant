plugins {
    id("java")
    id("war")
}

group = "com.makepizza"

repositories {
    mavenCentral()
}


dependencies {
    implementation("org.springframework:spring-core:6.1.14")
    implementation("org.springframework:spring-context:6.1.14")
    implementation("org.springframework:spring-web:6.1.14")
    implementation("org.springframework:spring-webmvc:6.1.14")
    compileOnly("jakarta.servlet:jakarta.servlet-api:6.1.0")

    implementation("org.thymeleaf:thymeleaf-spring6:3.1.2.RELEASE")

//    implementation("org.springframework:spring-orm:6.1.14")
    implementation("org.springframework.data:spring-data-jpa:3.3.5")
    implementation("org.hibernate.orm:hibernate-core:6.6.1.Final")
    implementation("org.postgresql:postgresql:42.7.4")

    implementation("org.apache.logging.log4j:log4j-core:2.24.1")

}