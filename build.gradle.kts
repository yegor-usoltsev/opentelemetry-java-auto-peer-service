import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    `java-library`
    jacoco
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("io.opentelemetry.javaagent:opentelemetry-javaagent-extension-api:2.21.0-alpha")
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-core:5.20.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

configurations {
    testImplementation {
        extendsFrom(configurations.compileOnly.get())
    }
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(17)
}

tasks.test {
    testLogging {
        events = setOf(TestLogEvent.PASSED, TestLogEvent.SKIPPED, TestLogEvent.FAILED)
        exceptionFormat = TestExceptionFormat.FULL
        showExceptions = true
    }
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    reports {
        html.required = true
        xml.required = true
    }
    dependsOn(tasks.test)
}
