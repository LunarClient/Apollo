package com.lunarclient.apollo.module.cooldown;

import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.module.ModuleDefinition;
import com.lunarclient.apollo.player.ApolloPlayer;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the cooldown module.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
@ModuleDefinition(id = "cooldown", name = "Cooldown")
public abstract class CooldownModule extends ApolloModule {

    /**
     * Displays the {@link Cooldown} to the {@link ApolloPlayer}.
     *
     * @param viewer the player who is receiving the packet
     * @param cooldown the cooldown
     * @since 1.0.0
     */
    public abstract void displayCooldown(ApolloPlayer viewer, Cooldown cooldown);

    /**
     * Removes the {@link Cooldown} from the {@link ApolloPlayer}.
     *
     * @param viewer the player who is receiving the packet
     * @param cooldownName the cooldown name
     * @since 1.0.0
     */
    public abstract void removeCooldown(ApolloPlayer viewer, String cooldownName);

    /**
     * Removes the {@link Cooldown} from the {@link ApolloPlayer}.
     *
     * @param viewer the player who is receiving the packet
     * @param cooldown the cooldown
     * @since 1.0.0
     */
    public abstract void removeCooldown(ApolloPlayer viewer, Cooldown cooldown);

    /**
     * Resets all {@link Cooldown}s for the {@link ApolloPlayer}.
     *
     * @param viewer the player who is receiving the packet
     * @since 1.0.0
     */
    public abstract void resetCooldowns(ApolloPlayer viewer);

}
