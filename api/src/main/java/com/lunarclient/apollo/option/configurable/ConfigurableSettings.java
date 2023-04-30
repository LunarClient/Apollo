package com.lunarclient.apollo.option.configurable;

import java.util.Set;
import lombok.Builder;
import lombok.Getter;

/**
 * Represents configurable settings.
 *
 * @since 1.0.0
 */
@Getter
@Builder
public class ConfigurableSettings {

    /**
     * Returns a {@link Set} of {@link Configurable}s.
     *
     * @return the configurable set
     * @since 1.0.0
     */
    Set<Configurable> settings;

}
