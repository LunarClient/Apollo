package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ui.Nametag;
import java.util.UUID;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the nametag module.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
public abstract class Nametags extends ApolloModule {

    Nametags() {
        super("Nametags");
    }

    /**
     * Overrides the {@link Nametag} for the {@link ApolloPlayer}s.
     *
     * @param nametag the nametag
     * @param viewers the viewers
     * @since 1.0.0
     */
    public abstract void overrideNametag(Nametag nametag, ApolloPlayer... viewers);

    /**
     * Resets the {@link Nametag} for the {@link ApolloPlayer}s.
     *
     * @param playerUuid the player uuid
     * @param viewers the viewers
     * @since 1.0.0
     */
    public abstract void resetNametag(UUID playerUuid, ApolloPlayer... viewers);

    /**
     * Resets the {@link Nametag} for the {@link ApolloPlayer}s.
     *
     * @param nametag the nametag
     * @param viewers the viewers
     * @since 1.0.0
     */
    public abstract void resetNametag(Nametag nametag, ApolloPlayer... viewers);

    /**
     * Resets all {@link Nametag}s for the {@link ApolloPlayer}.
     *
     * @param player the player
     * @since 1.0.0
     */
    public abstract void resetNametags(ApolloPlayer player);

}
