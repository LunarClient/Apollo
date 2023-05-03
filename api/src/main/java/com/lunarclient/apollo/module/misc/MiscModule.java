package com.lunarclient.apollo.module.misc;

import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.module.ModuleDefinition;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.util.List;
import java.util.UUID;
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
     * Overrides the {@link UUID} rainbow for the {@link ApolloPlayer}.
     *
     * @param viewer the player who is receiving the packet
     * @param sheepUuids the UUIDs of the sheep to manipulate
     * @since 1.0.0
     */
    public abstract void overrideRainbowSheep(ApolloPlayer viewer, List<UUID> sheepUuids);

    /**
     * Resets the {@link UUID} rainbow for the {@link ApolloPlayer}.
     *
     * @param viewer the player who is receiving the packet
     * @param sheepUuids the UUIDs of the sheep to manipulate
     * @since 1.0.0
     */
    public abstract void resetRainbowSheep(ApolloPlayer viewer, List<UUID> sheepUuids);

    /**
     * Flips the {@link UUID} for the {@link ApolloPlayer}.
     *
     * @param viewer the player who is receiving the packet
     * @param entityUuids the UUIDs of the entities to flip
     * @since 1.0.0
     */
    public abstract void flipEntity(ApolloPlayer viewer, List<UUID> entityUuids);

    /**
     * Resets the {@link UUID} flip for the {@link ApolloPlayer}.
     *
     * @param viewer the player who is receiving the packet
     * @param entityUuids the UUIDs of the entities to reset (unflip)
     * @since 1.0.0
     */
    public abstract void resetFlippedEntity(ApolloPlayer viewer, List<UUID> entityUuids);

}
