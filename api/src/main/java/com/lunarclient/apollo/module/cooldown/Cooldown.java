package com.lunarclient.apollo.module.cooldown;

import com.lunarclient.apollo.common.icon.Icon;
import java.time.Duration;
import lombok.Builder;
import lombok.Getter;

/**
 * Represents a cooldown which can be shown on the client.
 *
 * @since 1.0.0
 */
@Getter
@Builder
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
     * Returns the cooldown {@link Icon}.
     *
     * @return the cooldown icon
     * @since 1.0.0
     */
    Icon icon;

}
