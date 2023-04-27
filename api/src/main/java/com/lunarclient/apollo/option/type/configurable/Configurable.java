package com.lunarclient.apollo.option.type.configurable;

import java.util.Map;
import lombok.Value;

/**
 * Represents an configurable.
 *
 * @since 1.0.0
 */
@Value(staticConstructor = "of")
public class Configurable {

    /**
     * Returns the configurable {@link String} target.
     *
     * @return the configurable target
     * @since 1.0.0
     */
    String target;

    /**
     * Returns the configurable {@link Boolean} enabled state.
     *
     * @return the enabled state
     * @since 1.0.0
     */
    boolean enable;

    /**
     * Returns the configurable properties {@link Map} that contains {@link String}
     * option id as key and {@link Object} forced value as value.
     *
     * @return the properties map
     * @since 1.0.0
     */
    Map<String, Object> properties;

}
