package com.lunarclient.apollo.module.notification;

import lombok.Builder;
import lombok.Getter;

/**
 * Represents a notification which can be shown on the client.
 *
 * @since 1.0.0
 */
@Getter
@Builder
public final class Notification {

    /**
     * Returns the notification {@link String} title.
     *
     * @return the notification title
     * @since 1.0.0
     */
    String title;

    /**
     * Returns the notification {@link String} description.
     *
     * @return the notification description
     * @since 1.0.0
     */
    String description;

    /**
     * Returns the notification {@link String} resource location.
     *
     * <p>Represents an icon that will appear for the player
     * if empty (null) it'll display a generic info message</p>
     *
     * @return the notification resource location
     * @since 1.0.0
     */
    String resourceLocation;

}
