package com.lunarclient.apollo.module.hologram;

import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.util.Collection;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the hologram module.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
public abstract class HologramModule extends ApolloModule {

    HologramModule() {
        super("Hologram");
    }

    /**
     * Adds or updates the {@link Hologram} to the {@link ApolloPlayer}s.
     *
     * @param hologram the hologram
     * @param viewers the viewers
     * @since 1.0.0
     */
    public abstract void displayHologram(Collection<ApolloPlayer> viewers, Hologram hologram);

    /**
     * Removes the {@link Hologram} for the {@link ApolloPlayer}s.
     *
     * @param hologramId the hologram id
     * @param viewers the viewers
     * @since 1.0.0
     */
    public abstract void removeHologram(Collection<ApolloPlayer> viewers, String hologramId);

    /**
     * Removes the {@link Hologram} for the {@link ApolloPlayer}s.
     *
     * @param viewers the players who are receiving the packet
     * @param hologram the hologram
     * @since 1.0.0
     */
    public abstract void removeHologram(Collection<ApolloPlayer> viewers, Hologram hologram);

    /**
     * Resets all {@link Hologram}s for the {@link ApolloPlayer}.
     *
     * @param viewer the player who is receiving the packet
     * @since 1.0.0r
     */
    public abstract void resetHolograms(ApolloPlayer viewer);

}
