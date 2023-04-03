package com.moonsworth.apollo.module.type;

import com.moonsworth.apollo.module.ApolloModule;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.player.ApolloPlayerVersion;
import com.moonsworth.apollo.player.ui.Title;
import org.jetbrains.annotations.ApiStatus;

import java.util.EnumSet;
import java.util.Set;

/**
 * Represents the title module.
 * <p>
 * This module represents titles added in
 * version 1.8 (47) and is back-ported to 1.7
 * therefore it's only supported on 1.7
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
public abstract class Titles extends ApolloModule {

    Titles() {
        super("Titles");
    }

    @Override
    public Set<ApolloPlayerVersion> getSupportedClientVersions() {
        return EnumSet.of(ApolloPlayerVersion.v1_7);
    }

    /**
     * Sends the {@link Title} to the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param title the title
     * @since 1.0.0
     */
    public abstract void sendTitle(final ApolloPlayer player, final Title title);

    /**
     * Sends the {@link Title} to all {@link ApolloPlayer}s.
     *
     * @param title the title
     * @since 1.0.0
     */
    public abstract void broadcastTitle(final Title title);
}
