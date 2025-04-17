package xyz.usoltsev.opentelemetry;

import io.opentelemetry.sdk.autoconfigure.spi.AutoConfigurationCustomizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class AutoPeerServiceAutoConfigurationCustomizerProviderTest {

    private AutoConfigurationCustomizer autoConfiguration;

    @BeforeEach
    void setUp() {
        autoConfiguration = mock(AutoConfigurationCustomizer.class);
    }

    @Test
    void customize_shouldAddSpanProcessor() {
        // Given
        AutoPeerServiceAutoConfigurationCustomizerProvider provider = new AutoPeerServiceAutoConfigurationCustomizerProvider();

        // When
        provider.customize(autoConfiguration);

        // Then
        verify(autoConfiguration).addTracerProviderCustomizer(any());
    }

}
