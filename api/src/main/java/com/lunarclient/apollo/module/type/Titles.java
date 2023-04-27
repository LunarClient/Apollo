package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayerVersion;
import com.lunarclient.apollo.player.ui.Title;
import java.util.EnumSet;
import java.util.Set;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the title module.
 *
 * <p>This module represents titles added in version 1.8 (47) and is
 * back-ported to 1.7 therefore it's only supported on 1.7.</p>
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
    public abstract void displayTitleMessage(ApolloPlayer player, Title title);

    /**
     * Sends the {@link Title} to all {@link ApolloPlayer}s.
     *
     * @param title the title
     * @since 1.0.0
     */
    public abstract void broadcastTitle(Title title);

    /**
     * Resets all {@link Title}s for the {@link ApolloPlayer}.
     *
     * @param player the player
     * @since 1.0.0
     */
    public abstract void resetTitles(ApolloPlayer player);

}
