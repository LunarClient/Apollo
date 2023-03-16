package com.moonsworth.apollo.event;

/**
 * Represents a cancellable {@link Event}.
 *
 * @see Event
 * @since 1.0.0
 */
public interface EventCancellable extends Event {

    boolean isCancelled();

    void setCancelled(final boolean cancel);

}
