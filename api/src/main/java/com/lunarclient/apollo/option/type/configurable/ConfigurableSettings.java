package com.lunarclient.apollo.option.type.configurable;

import java.util.Set;
import lombok.Value;

/**
 * Represents configurable settings.
 *
 * @since 1.0.0
 */
@Value(staticConstructor = "of")
public class ConfigurableSettings {

    /**
     * Returns a {@link Set} of {@link Configurable}s.
     *
     * @return the configurable set
     * @since 1.0.0
     */
    Set<Configurable> settings;

}
