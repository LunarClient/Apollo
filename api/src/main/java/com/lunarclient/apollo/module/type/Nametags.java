package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ui.Nametag;
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
     * Sends the {@link Nametag} to the {@link ApolloPlayer}s.
     *
     * @param nametag the nametag
     * @param viewers the viewers
     * @since 1.0.0
     */
    public abstract void overrideNametag(Nametag nametag, ApolloPlayer... viewers);

    /**
     * Resets the {@link Nametag} for the {@link ApolloPlayer}s.
     *
     * @param viewers the viewers
     * @since 1.0.0
     */
    public abstract void resetNametag(ApolloPlayer... viewers);
}
