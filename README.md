# opentelemetry-java-auto-peer-service

[![Build Status](https://github.com/yegor-usoltsev/opentelemetry-java-auto-peer-service/actions/workflows/CI.yml/badge.svg)](https://github.com/yegor-usoltsev/opentelemetry-java-auto-peer-service/actions)
[![GitHub Release](https://img.shields.io/github/v/release/yegor-usoltsev/opentelemetry-java-auto-peer-service?sort=semver)](https://github.com/yegor-usoltsev/opentelemetry-java-auto-peer-service/releases)

An extension for the OpenTelemetry Java agent designed to enrich HTTP client spans. It does this by automatically setting the `peer.service` attribute based on the `server.address` attribute.

This extension acts as an alternative to the built-in [`otel.instrumentation.common.peer-service-mapping`](https://opentelemetry.io/docs/zero-code/java/agent/instrumentation/#peer-service-name), which maps hostnames/IPs directly to a peer service name.

The build produces a single JAR: [`opentelemetry-java-auto-peer-service.jar`](https://github.com/yegor-usoltsev/opentelemetry-java-auto-peer-service/releases/latest/download/opentelemetry-java-auto-peer-service.jar). Use it as an extension for the OpenTelemetry Java agent to customize how peer service names are assigned.

## Usage

Download the latest JAR and launch your application with the following command:

```bash
wget https://github.com/yegor-usoltsev/opentelemetry-java-auto-peer-service/releases/latest/download/opentelemetry-java-auto-peer-service.jar

java -javaagent:opentelemetry-javaagent.jar \
     -Dotel.javaagent.extensions=opentelemetry-java-auto-peer-service.jar \
     -jar app.jar
```

## Versioning

This project uses [Semantic Versioning](https://semver.org)

## Contributing

Pull requests are welcome. For major changes, please [open an issue](https://github.com/yegor-usoltsev/opentelemetry-java-auto-peer-service/issues/new) first to discuss what you would like to change. Please make sure to update tests as appropriate.

## License

[MIT](https://github.com/yegor-usoltsev/opentelemetry-java-auto-peer-service/blob/main/LICENSE)
