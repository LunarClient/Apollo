package com.moonsworth.apollo.player.ui;

import java.util.Map;
import lombok.Value;

/**
 * Represents mod settings which can sent to the client.
 *
 * @since 1.0.0
 */
@Value(staticConstructor = "of")
public class ModSetting {

    /**
     * Returns the setting {@link String} mod id.
     *
     * @return the setting mod id
     * @since 1.0.0
     */
    String modId;

    /**
     * Returns the setting {@link Boolean} enabled state.
     *
     * @return the enabled state
     * @since 1.0.0
     */
    boolean enabled;

    /**
     * Returns the setting properties {@link Map} that contains {@link String}
     * option id as key and {@link String} forced value as value.
     *
     * @return the settings map
     * @since 1.0.0
     */
    Map<String, String> properties;
}
