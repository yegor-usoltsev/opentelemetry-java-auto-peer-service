# opentelemetry-java-auto-peer-service

[![Build Status](https://github.com/yegor-usoltsev/opentelemetry-java-auto-peer-service/actions/workflows/ci.yml/badge.svg)](https://github.com/yegor-usoltsev/opentelemetry-java-auto-peer-service/actions)
[![Codecov](https://codecov.io/github/yegor-usoltsev/opentelemetry-java-auto-peer-service/graph/badge.svg?token=0MC613WZN0)](https://codecov.io/github/yegor-usoltsev/opentelemetry-java-auto-peer-service)
[![GitHub Release](https://img.shields.io/github/v/release/yegor-usoltsev/opentelemetry-java-auto-peer-service?sort=semver)](https://github.com/yegor-usoltsev/opentelemetry-java-auto-peer-service/releases)

An extension for the OpenTelemetry Java agent designed to enrich HTTP client spans. It does this by automatically setting the `peer.service` attribute based on the `server.address` attribute.

This extension acts as an alternative to the built-in [`otel.instrumentation.common.peer-service-mapping`](https://opentelemetry.io/docs/zero-code/java/agent/instrumentation/#peer-service-name), which maps hostnames/IPs directly to a peer service name.

The build produces a single JAR: [`opentelemetry-java-auto-peer-service.jar`](https://github.com/yegor-usoltsev/opentelemetry-java-auto-peer-service/releases/latest/download/opentelemetry-java-auto-peer-service.jar). Use it as an extension for the OpenTelemetry Java agent to customize how peer service names are assigned.

## Usage

Requires Java 17 or later and an OpenTelemetry Java agent JAR. Download the extension and add it when launching your application:

```bash
wget https://github.com/yegor-usoltsev/opentelemetry-java-auto-peer-service/releases/latest/download/opentelemetry-java-auto-peer-service.jar

java -javaagent:opentelemetry-javaagent.jar \
     -Dotel.javaagent.extensions=opentelemetry-java-auto-peer-service.jar \
     -jar app.jar
```

## Behavior

For a `CLIENT` span, the extension sets `peer.service` to `server.address` when `peer.service` is unset and `server.address` is present. It leaves non-client spans and existing `peer.service` values unchanged.

For example, a client span with `server.address = inventory.example.com` receives `peer.service = inventory.example.com`.

## License

[MIT](https://github.com/yegor-usoltsev/opentelemetry-java-auto-peer-service/blob/main/LICENSE)
