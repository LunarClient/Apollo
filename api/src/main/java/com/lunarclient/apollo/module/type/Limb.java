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
     * Toggles the {@link Armor} for the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param armor the armor
     * @since 1.0.0
     */
    public abstract void toggleArmorPart(ApolloPlayer player, Armor armor);

    /**
     * Toggles the {@link Body} for the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param body the body
     * @since 1.0.0
     */
    public abstract void toggleBodyPart(ApolloPlayer player, Body body);

    /**
     * Clears {@link Armor} and {@link Body} modifications for the {@link ApolloPlayer}.
     *
     * @param player the player
     * @since 1.0.0
     */
    public abstract void clearModifications(ApolloPlayer player);

}
