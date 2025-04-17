package xyz.usoltsev.opentelemetry;

import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.trace.SpanKind;
import io.opentelemetry.context.Context;
import io.opentelemetry.sdk.trace.ReadWriteSpan;
import io.opentelemetry.sdk.trace.ReadableSpan;
import io.opentelemetry.sdk.trace.SpanProcessor;

public class AutoPeerServiceSpanProcessor implements SpanProcessor {

    private static final AttributeKey<String> PEER_SERVICE_KEY = AttributeKey.stringKey("peer.service");
    private static final AttributeKey<String> SERVER_ADDRESS_KEY = AttributeKey.stringKey("server.address");

    @Override
    public void onStart(Context parentContext, ReadWriteSpan span) {
        if (span.getKind() != SpanKind.CLIENT) return;
        if (span.getAttribute(PEER_SERVICE_KEY) != null) return;
        String serverAddress = span.getAttribute(SERVER_ADDRESS_KEY);
        if (serverAddress == null) return;
        span.setAttribute(PEER_SERVICE_KEY, serverAddress);
    }

    @Override
    public boolean isStartRequired() {
        return true;
    }

    @Override
    public void onEnd(ReadableSpan span) {
        // no-op
    }

    @Override
    public boolean isEndRequired() {
        return false;
    }

}
