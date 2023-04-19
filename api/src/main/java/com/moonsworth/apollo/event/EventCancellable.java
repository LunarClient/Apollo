package com.moonsworth.apollo.event;

/**
 * Represents a cancellable {@link Event}.
 *
 * @see Event
 * @since 1.0.0
 */
public interface EventCancellable extends Event {

    /**
     * Returns whether the event is cancelled.
     *
     * @return true if the event is cancelled, false otherwise
     * @since 1.0.0
     */
    boolean isCancelled();

    /**
     * Sets whether the event is cancelled.
     *
     * @param cancel true if the event is cancelled, false otherwise
     * @since 1.0.0
     */
    void setCancelled(boolean cancel);

}
