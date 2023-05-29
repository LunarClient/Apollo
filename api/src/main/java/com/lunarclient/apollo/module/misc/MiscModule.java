package com.lunarclient.apollo.module.misc;

import com.lunarclient.apollo.common.ApolloEntity;
import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.module.ModuleDefinition;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.util.List;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the misc module.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
@ModuleDefinition(id = "misc", name = "Misc")
public abstract class MiscModule extends ApolloModule {

    /**
     * Displays the {@link Vignette} to the {@link ApolloPlayer}.
     *
     * @param viewer the player who is receiving the packet
     * @param vignette the vignette
     * @since 1.0.0
     */
    public abstract void displayVignette(ApolloPlayer viewer, Vignette vignette);

    /**
     * Resets the {@link Vignette} for the {@link ApolloPlayer}.
     *
     * @param viewer the player who is receiving the packet
     * @since 1.0.0
     */
    public abstract void resetVignette(ApolloPlayer viewer);

    /**
     * Overrides the {@link ApolloEntity} rainbow for the {@link ApolloPlayer}.
     *
     * @param viewer the player who is receiving the packet
     * @param sheepEntities the list of sheep entities
     * @since 1.0.0
     */
    public abstract void overrideRainbowSheep(ApolloPlayer viewer, List<ApolloEntity> sheepEntities);

    /**
     * Resets the {@link ApolloEntity} rainbow for the {@link ApolloPlayer}.
     *
     * @param viewer the player who is receiving the packet
     * @param sheepEntities the list of sheep entities to manipulate
     * @since 1.0.0
     */
    public abstract void resetRainbowSheep(ApolloPlayer viewer, List<ApolloEntity> sheepEntities);

    /**
     * Flips the {@link ApolloEntity} for the {@link ApolloPlayer}.
     *
     * @param viewer the player who is receiving the packet
     * @param entities the entities to flip
     * @since 1.0.0
     */
    public abstract void flipEntity(ApolloPlayer viewer, List<ApolloEntity> entities);

    /**
     * Resets the {@link ApolloEntity} flip for the {@link ApolloPlayer}.
     *
     * @param viewer the player who is receiving the packet
     * @param entities the entities to reset (unflip)
     * @since 1.0.0
     */
    public abstract void resetFlippedEntity(ApolloPlayer viewer, List<ApolloEntity> entities);

}
