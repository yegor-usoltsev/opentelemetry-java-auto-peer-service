import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("io.opentelemetry.javaagent:opentelemetry-javaagent-extension-api:2.14.0-alpha")
    testImplementation("org.junit.jupiter:junit-jupiter:5.12.2")
    testImplementation("org.mockito:mockito-core:4.11.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

configurations {
    testImplementation {
        extendsFrom(configurations.compileOnly.get())
    }
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(8)
}

tasks.test {
    testLogging {
        events = setOf(TestLogEvent.PASSED, TestLogEvent.SKIPPED, TestLogEvent.FAILED)
        exceptionFormat = TestExceptionFormat.FULL
        showExceptions = true
    }
    useJUnitPlatform()
}
