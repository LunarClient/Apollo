package com.lunarclient.apollo.event;

import java.util.function.Consumer;

/**
 * Represents an event listener.
 *
 * @since 1.0.0
 */
public interface ApolloListener {

    /**
     * Shorthand for {@link EventBus#register(Class, Consumer)}.
     *
     * @param event the event class
     * @param consumer the listener
     * @param <T> the event type
     * @since 1.0.0
     */
    default <T extends Event> void handle(Class<T> event, Consumer<T> consumer) {
        EventBus.getBus().register(event, consumer);
    }

}
