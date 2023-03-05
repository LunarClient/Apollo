package com.moonsworth.apollo.api.player.ui;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents a cooldown which can be shown on the client.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
public interface Cooldown {

    /**
     * Creates a new builder for a {@link Cooldown} instance.
     *
     * @return a new cooldown builder
     * @since 1.0.0
     */
    static Cooldown.Builder cooldown() {
        return new CooldownImpl.BuilderImpl();
    }

    /**
     * Creates a new builder for a {@link Cooldown} instance, using the values
     * from the specified existing {@link Cooldown}.
     *
     * @param existing the existing cooldown
     * @return a new cooldown builder
     * @since 1.0.0
     */
    static Cooldown.Builder cooldown(final Cooldown existing) {
        return new CooldownImpl.BuilderImpl(existing);
    }

    /**
     * Returns the cooldown {@link String} name.
     *
     * @return the cooldown name
     * @since 1.0.0
     */
    String getName();

    /**
     * Returns the cooldown {@link Duration}.
     *
     * @return the cooldown duration
     * @since 1.0.0
     */
    Duration getDuration();

    /**
     * A builder for creating {@link Cooldown} instances.
     *
     * @since 1.0.0
     */
    interface Builder {

        /**
         * Sets the cooldown {@link String} name.
         *
         * @param name the cooldown name
         * @return this builder
         * @since 1.0.0
         */
        Builder name(final String name);

        /**
         * Sets the cooldown duration.
         *
         * @param time the duration period
         * @param timeUnit the duration time unit
         * @return this builder
         * @see Cooldown.Builder#duration(Duration)
         * @since 1.0.0
         */
        Builder duration(final long time, final TimeUnit timeUnit);

        /**
         * Sets the cooldown {@link Duration}.
         *
         * @param duration the duration
         * @return this builder
         * @since 1.0.0
         */
        Builder duration(final Duration duration);

        /**
         * Returns a new {@link Cooldown} instance from this builder.
         *
         * @return a new cooldown
         * @since 1.0.0
         */
        Cooldown build();

    }

}
