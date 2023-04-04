package com.moonsworth.apollo.module.type;

import com.moonsworth.apollo.module.ApolloModule;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.player.ui.Cooldown;
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
     * Sends the {@link Cooldown} to the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param cooldown the cooldown
     * @since 1.0.0
     */
    public abstract void sendCooldown(final ApolloPlayer player, final Cooldown cooldown);

    /**
     * Removes the {@link Cooldown} from the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param cooldown the cooldown
     * @since 1.0.0
     */
    public abstract void clearCooldown(final ApolloPlayer player, final Cooldown cooldown);

    /**
     * Clears all {@link Cooldown}s from the {@link ApolloPlayer}.
     *
     * @param player the player
     * @since 1.0.0
     */
    public abstract void clearCooldowns(final ApolloPlayer player);

}
