package com.moonsworth.apollo.player.ui;

import lombok.Value;

import java.time.Duration;

/**
 * Represents a cooldown which can be shown on the client.
 *
 * @since 1.0.0
 */
@Value(staticConstructor = "of")
public class Cooldown {

    /**
     * Returns the cooldown {@link String} name.
     *
     * @return the cooldown name
     * @since 1.0.0
     */
    String name;

    /**
     * Returns the cooldown {@link Duration}.
     *
     * @return the cooldown duration
     * @since 1.0.0
     */
    Duration duration;

}
