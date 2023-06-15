package com.lunarclient.apollo.module.title;

import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.module.ModuleDefinition;
import com.lunarclient.apollo.player.ApolloPlayer;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the title module.
 *
 * <p>This module represents titles added in
 * version 1.8 (47) and is back-ported to 1.7.</p>
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
@ModuleDefinition(id = "title", name = "Title")
public abstract class TitleModule extends ApolloModule {

    /**
     * Sends the {@link Title} to the {@link ApolloPlayer}.
     *
     * @param viewer the player who is receiving the packet
     * @param title the title
     * @since 1.0.0
     */
    public abstract void displayTitle(ApolloPlayer viewer, Title title);

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
     * @param viewer the player who is receiving the packet
     * @since 1.0.0
     */
    public abstract void resetTitles(ApolloPlayer viewer);

}
