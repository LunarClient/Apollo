package com.lunarclient.apollo.module.nametag;

import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.util.Collection;
import java.util.UUID;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the nametag module.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
public abstract class NametagModule extends ApolloModule {

    NametagModule() {
        super("Nametag");
    }

    /**
     * Overrides the {@link Nametag} for the {@link ApolloPlayer}s.
     *
     * @param viewers the viewers
     * @param playerUuid the player whose nametag we are manipulating
     * @param nametag the nametag
     * @since 1.0.0
     */
    public abstract void overrideNametag(Collection<ApolloPlayer> viewers, UUID playerUuid, Nametag nametag);

    /**
     * Resets the {@link Nametag} for the {@link ApolloPlayer}s.
     *
     * @param viewers the viewers
     * @param playerUuid the player whose nametag we are manipulating
     * @since 1.0.0
     */
    public abstract void resetNametag(Collection<ApolloPlayer> viewers, UUID playerUuid);

    /**
     * Resets all {@link Nametag}s for the {@link ApolloPlayer}.
     *
     * @param viewer the player who is receiving the packet
     * @since 1.0.0
     */
    public abstract void resetNametags(ApolloPlayer viewer);

}
