package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ui.Cooldown;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the cooldown module.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
public abstract class Cooldowns extends ApolloModule {

    Cooldowns() {
        super("Cooldowns");
    }

    /**
     * Displays the {@link Cooldown} for the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param cooldown the cooldown
     * @since 1.0.0
     */
    public abstract void displayCooldown(ApolloPlayer player, Cooldown cooldown);

    /**
     * Removes the {@link Cooldown} from the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param cooldownName the cooldown name
     * @since 1.0.0
     */
    public abstract void removeCooldown(ApolloPlayer player, String cooldownName);

    /**
     * Removes the {@link Cooldown} from the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param cooldown the cooldown
     * @since 1.0.0
     */
    public abstract void removeCooldown(ApolloPlayer player, Cooldown cooldown);

    /**
     * Resets all {@link Cooldown}s from the {@link ApolloPlayer}.
     *
     * @param player the player
     * @since 1.0.0
     */
    public abstract void resetCooldowns(ApolloPlayer player);

}
