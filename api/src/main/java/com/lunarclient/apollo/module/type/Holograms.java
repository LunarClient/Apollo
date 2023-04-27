package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ui.Hologram;
import java.util.UUID;
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
    public abstract void displayHologram(Hologram hologram, ApolloPlayer... viewers);

    /**
     * Removes the {@link Hologram} for the {@link ApolloPlayer}s.
     *
     * @param hologramId the hologram id
     * @param viewers the viewers
     * @since 1.0.0
     */
    public abstract void removeHologram(UUID hologramId, ApolloPlayer... viewers);

    /**
     * Removes the {@link Hologram} for the {@link ApolloPlayer}s.
     *
     * @param hologram the hologram
     * @param viewers the viewers
     * @since 1.0.0
     */
    public abstract void removeHologram(Hologram hologram, ApolloPlayer... viewers);

    /**
     * Resets all {@link Hologram}s for the {@link ApolloPlayer}.
     *
     * @param player the player
     * @since 1.0.0
     */
    public abstract void resetHolograms(ApolloPlayer player);

}
