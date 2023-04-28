package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ui.limb.Armor;
import com.lunarclient.apollo.player.ui.limb.Body;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the limb module.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
public abstract class Limb extends ApolloModule {

    Limb() {
        super("Limb");
    }

    /**
     * Hides the {@link Armor} for the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param armor the armor
     * @since 1.0.0
     */
    public abstract void hideArmorPieces(ApolloPlayer player, Armor armor);

    /**
     * Resets the {@link Armor} for the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param armor the armor
     * @since 1.0.0
     */
    public abstract void resetArmorPieces(ApolloPlayer player,
                                          Armor armor);

    /**
     * Hides the {@link Body} for the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param body the body
     * @since 1.0.0
     */
    public abstract void hideBodyParts(ApolloPlayer player, Body body);

    /**
     * Resets the {@link Body} for the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param body the body
     * @since 1.0.0
     */
    public abstract void resetBodyParts(ApolloPlayer player, Body body);

}
