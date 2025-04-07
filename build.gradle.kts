plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("io.opentelemetry.javaagent:opentelemetry-javaagent-extension-api:2.14.0-alpha")
}
