package com.moonsworth.apollo.module.type;

import com.moonsworth.apollo.module.ApolloModule;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.player.ui.Hologram;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the hologram module.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
public abstract class Holograms extends ApolloModule {

    Holograms() {
        super("Holograms");
    }

    /**
     * Adds or updates the {@link Hologram} to the {@link ApolloPlayer}s.
     *
     * @param hologram the hologram
     * @param viewers the viewers
     * @since 1.0.0
     */
    public abstract void addOrUpdateHologram(Hologram hologram, ApolloPlayer... viewers);

    /**
     * Removes the {@link Hologram} for the {@link ApolloPlayer}s.
     *
     * @param hologram the hologram
     * @param viewers the viewers
     * @since 1.0.0
     */
    public abstract void removeHologram(Hologram hologram, ApolloPlayer... viewers);
}
