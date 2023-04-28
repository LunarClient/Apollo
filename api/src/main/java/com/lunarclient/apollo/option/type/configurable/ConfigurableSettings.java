package com.lunarclient.apollo.option.type.configurable;

import java.util.Set;
import lombok.Builder;
import lombok.Getter;

/**
 * Represents configurable settings.
 *
 * @since 1.0.0
 */
@Getter
@Builder(setterPrefix = "with")
public class ConfigurableSettings {

    /**
     * Returns a {@link Set} of {@link Configurable}s.
     *
     * @return the configurable set
     * @since 1.0.0
     */
    Set<Configurable> settings;

}
