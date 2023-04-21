package com.lunarclient.apollo.player.ui;

import com.lunarclient.apollo.option.type.icon.Icon;
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

    /**
     * Returns the cooldown {@link Integer} item id.
     *
     * <p>You can provide the item id or the renderable icon.</p>
     *
     * @return the cooldown item id
     * @since 1.0.0
     */
    int itemId;

    /**
     * Returns the cooldown {@link Icon}.
     *
     * @return the cooldown icon
     * @since 1.0.0
     */
    Icon icon;

}
