package com.lunarclient.apollo.player.ui;

import java.time.Duration;
import lombok.Value;

/**
 * Represents a title which can be shown on the client.
 *
 * @since 1.0.0
 */
@Value(staticConstructor = "of")
public class Title {

    /**
     * Returns the title {@link Type} type.
     *
     * @return the title type
     * @since 1.0.0
     */
    Type type;

    /**
     * Returns the title {@link String} message.
     *
     * @return the title message
     * @since 1.0.0
     */
    String message;

    /**
     * Returns the title {@link Float} scale.
     *
     * @return the title scale
     * @since 1.0.0
     */
    float scale;

    /**
     * Returns the title {@link Duration} display time.
     *
     * @return the title display time
     * @since 1.0.0
     */
    Duration displayTime;

    /**
     * Returns the title {@link Duration} fade in time.
     *
     * @return the title fade in time
     * @since 1.0.0
     */
    Duration fadeInTime;

    /**
     * Returns the title {@link Duration} fade out time.
     *
     * @return the title fade out time
     * @since 1.0.0
     */
    Duration fadeOutTime;

    /**
     * Represents the title type.
     *
     * @since 1.0.0
     */
    public enum Type {
        TITLE,
        SUBTITLE
    }
}
