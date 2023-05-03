package com.lunarclient.apollo.module.limb;

import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.module.ModuleDefinition;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.util.Collection;
import java.util.UUID;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the limb module.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
@ModuleDefinition(id = "limb", name = "Limb")
public abstract class LimbModule extends ApolloModule {

    /**
     * Hides the {@link ArmorPiece} for the {@link ApolloPlayer}.
     *
     * @param viewer the player who is receiving the packet
     * @param playerUuid the player whose armor we will be manipulating
     * @param armorPieces the armor pieces to hide
     * @since 1.0.0
     */
    public abstract void hideArmorPieces(ApolloPlayer viewer, UUID playerUuid, Collection<ArmorPiece> armorPieces);

    /**
     * Resets the {@link ArmorPiece} for the {@link ApolloPlayer}.
     *
     * @param viewer the player who is receiving the packet
     * @param playerUuid the player whose armor we will be manipulating
     * @param armorPieces the armor pieces to reset (show)
     * @since 1.0.0
     */
    public abstract void resetArmorPieces(ApolloPlayer viewer, UUID playerUuid, Collection<ArmorPiece> armorPieces);

    /**
     * Hides the {@link BodyPart} for the {@link ApolloPlayer}.
     *
     * @param viewer the player who is receiving the packet
     * @param playerUuid the player whose body we will be manipulating
     * @param bodyParts the body parts to hide
     * @since 1.0.0
     */
    public abstract void hideBodyParts(ApolloPlayer viewer, UUID playerUuid, Collection<BodyPart> bodyParts);

    /**
     * Resets the {@link BodyPart} for the {@link ApolloPlayer}.
     *
     * @param viewer the player who is receiving the packet
     * @param playerUuid the player whose body we will be manipulating
     * @param bodyParts the body parts to reset (show)
     * @since 1.0.0
     */
    public abstract void resetBodyParts(ApolloPlayer viewer, UUID playerUuid, Collection<BodyPart> bodyParts);

}
