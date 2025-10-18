package xyz.usoltsev.opentelemetry;

import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.trace.SpanKind;
import io.opentelemetry.context.Context;
import io.opentelemetry.sdk.trace.ReadWriteSpan;
import io.opentelemetry.sdk.trace.ReadableSpan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SuppressWarnings("unchecked")
class AutoPeerServiceSpanProcessorTest {

    private AutoPeerServiceSpanProcessor processor;
    private Context parentContext;
    private ReadWriteSpan span;

    @BeforeEach
    void setUp() {
        processor = new AutoPeerServiceSpanProcessor();
        parentContext = mock(Context.class);
        span = mock(ReadWriteSpan.class);
    }

    @Test
    void onStart_shouldSetPeerService_whenClientSpanAndServerAddressPresent() {
        // Given
        when(span.getKind()).thenReturn(SpanKind.CLIENT);
        when(span.getAttribute(AttributeKey.stringKey("server.address"))).thenReturn("example.com");
        when(span.getAttribute(AttributeKey.stringKey("peer.service"))).thenReturn(null);

        // When
        processor.onStart(parentContext, span);

        // Then
        verify(span).setAttribute(AttributeKey.stringKey("peer.service"), "example.com");
    }

    @Test
    void onStart_shouldNotSetPeerService_whenNotClientSpan() {
        // Given
        when(span.getKind()).thenReturn(SpanKind.SERVER);

        // When
        processor.onStart(parentContext, span);

        // Then
        verify(span, never()).setAttribute(any(AttributeKey.class), anyString());
    }

    @Test
    void onStart_shouldNotSetPeerService_whenPeerServiceAlreadySet() {
        // Given
        when(span.getKind()).thenReturn(SpanKind.CLIENT);
        when(span.getAttribute(AttributeKey.stringKey("peer.service"))).thenReturn("existing-service");

        // When
        processor.onStart(parentContext, span);

        // Then
        verify(span, never()).setAttribute(any(AttributeKey.class), anyString());
    }

    @Test
    void onStart_shouldNotSetPeerService_whenServerAddressNotPresent() {
        // Given
        when(span.getKind()).thenReturn(SpanKind.CLIENT);
        when(span.getAttribute(AttributeKey.stringKey("server.address"))).thenReturn(null);
        when(span.getAttribute(AttributeKey.stringKey("peer.service"))).thenReturn(null);

        // When
        processor.onStart(parentContext, span);

        // Then
        verify(span, never()).setAttribute(any(AttributeKey.class), anyString());
    }

    @Test
    void isStartRequired_shouldReturnTrue() {
        assertTrue(processor.isStartRequired());
    }

    @Test
    void onEnd_shouldDoNothing() {
        // Given
        ReadableSpan span = mock(ReadableSpan.class);

        // When
        processor.onEnd(span);

        // Then
        verifyNoInteractions(span);
    }

    @Test
    void isEndRequired_shouldReturnFalse() {
        assertFalse(processor.isEndRequired());
    }

}
