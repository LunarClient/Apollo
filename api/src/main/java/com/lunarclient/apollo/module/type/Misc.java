package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ui.misc.FlippedEntity;
import com.lunarclient.apollo.player.ui.misc.RainbowSheep;
import com.lunarclient.apollo.player.ui.misc.Vignette;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the misc module.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
public abstract class Misc extends ApolloModule {

    Misc() {
        super("Misc");
    }

    /**
     * Flips the {@link FlippedEntity} for the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param entities the entities
     * @since 1.0.0
     */
    public abstract void flipEntities(ApolloPlayer player, FlippedEntity... entities);

    /**
     * Flips all entities for the {@link ApolloPlayer}.
     *
     * @param player the player
     * @since 1.0.0
     */
    public abstract void flipAllEntities(ApolloPlayer player);

    /**
     * Makes the {@link RainbowSheep} sheep rainbow for the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param sheep the sheep
     * @since 1.0.0
     */
    public abstract void rainbowSheep(ApolloPlayer player, RainbowSheep... sheep);

    /**
     * Makes all sheep rainbow for the {@link ApolloPlayer}.
     *
     * @param player the player
     * @since 1.0.0
     */
    public abstract void rainbowSheep(ApolloPlayer player);

    /**
     * Sends the {@link Vignette} to the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param vignette the vignette
     * @since 1.0.0
     */
    public abstract void displayVignette(ApolloPlayer player, Vignette vignette);

}
