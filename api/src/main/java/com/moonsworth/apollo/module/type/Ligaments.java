package com.moonsworth.apollo.module.type;

import com.moonsworth.apollo.module.ApolloModule;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.player.ui.ligament.Armor;
import com.moonsworth.apollo.player.ui.ligament.Body;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the ligament module.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
public abstract class Ligaments extends ApolloModule {

    Ligaments() {
        super("Ligaments");
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
