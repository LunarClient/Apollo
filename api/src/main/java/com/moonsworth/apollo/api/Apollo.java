package com.moonsworth.apollo.api;

import com.moonsworth.apollo.api.bridge.ApolloPlayer;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Consumer;

/**
 * Main API class for Apollo.
 */
public class Apollo {

    /**
     * The plugin message channel that Lunar Client and Apollo talk over.
     */
    public static final String PLUGIN_MESSAGE_CHANNEL = "lunarclient:apollo";

    /**
     * The currently loaded platform. This is set as early as possible.
     */
    @Getter
    @Setter
    private static ApolloPlatform platform = null;

    public void withPlayer(Object o, Consumer<ApolloPlayer> consumer) {
        var apolloPlayer = platform.tryWrapPlayer(o);
        if (apolloPlayer != null) {
            consumer.accept(apolloPlayer);
        }
    }

}