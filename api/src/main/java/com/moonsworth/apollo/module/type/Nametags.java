package com.moonsworth.apollo.module.type;

import com.moonsworth.apollo.module.ApolloModule;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.player.ui.Nametag;
import com.moonsworth.apollo.player.ui.Notification;
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
    public abstract void overrideNametag(final Nametag nametag, final ApolloPlayer... viewers);

    /**
     * Resets the {@link Nametag} for the {@link ApolloPlayer}s.
     *
     * @param nametag the nametag
     * @param viewers the viewers
     * @since 1.0.0
     */
    public abstract void resetNametag(final Nametag nametag, final ApolloPlayer... viewers);
}
