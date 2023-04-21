package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ui.misc.Entity;
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
     * Displays the {@link Vignette} for the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param vignette the vignette
     * @since 1.0.0
     */
    public abstract void displayVignette(ApolloPlayer player, Vignette vignette);

    /**
     * Resets the {@link Vignette} for the {@link ApolloPlayer}.
     *
     * @param player the player
     * @since 1.0.0
     */
    public abstract void resetVignette(ApolloPlayer player);

    /**
     * Overrides the {@link Entity} rainbow for the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param sheep the sheep
     * @since 1.0.0
     */
    public abstract void overrideRainbowSheep(ApolloPlayer player, Entity... sheep);

    /**
     * Resets the {@link Entity} rainbow for the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param sheep the sheep
     * @since 1.0.0
     */
    public abstract void resetRainbowSheep(ApolloPlayer player, Entity... sheep);

    /**
     * Flips the {@link Entity} for the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param entities the entities
     * @since 1.0.0
     */
    public abstract void flipEntity(ApolloPlayer player, Entity... entities);

    /**
     * Resets the {@link Entity} flip for the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param entities the entities
     * @since 1.0.0
     */
    public abstract void resetFlippedEntity(ApolloPlayer player, Entity... entities);

}
