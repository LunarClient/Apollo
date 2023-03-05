package com.moonsworth.apollo.api.module.type;

import com.moonsworth.apollo.api.module.ApolloModule;
import com.moonsworth.apollo.api.player.ApolloPlayer;
import com.moonsworth.apollo.api.player.ui.Cooldown;

/**
 * Represents the cooldown module.
 *
 * @since 1.0.0
 */
public interface CooldownModule extends ApolloModule {

    /**
     * Sends the {@link Cooldown} to the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param cooldown the cooldown
     * @since 1.0.0
     */
    void sendCooldown(final ApolloPlayer player, final Cooldown cooldown);

    /**
     * Removes the {@link Cooldown} from the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param cooldown the cooldown
     * @since 1.0.0
     */
    void clearCooldown(final ApolloPlayer player, final Cooldown cooldown);

    /**
     * Clears all {@link Cooldown}s from the {@link ApolloPlayer}.
     *
     * @param player the player
     * @since 1.0.0
     */
    void clearCooldowns(final ApolloPlayer player);

}
