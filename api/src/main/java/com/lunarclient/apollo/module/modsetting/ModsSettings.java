package com.lunarclient.apollo.module.modsetting;

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
public final class ModsSettings {

    /**
     * Returns a {@link Set} of {@link ModSettings}s.
     *
     * @return the configurable set
     * @since 1.0.0
     */
    Set<ModSettings> settings;

}
