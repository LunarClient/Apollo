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
     * Sends the {@link Cooldown} to the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param cooldown the cooldown
     * @since 1.0.0
     */
    public abstract void sendCooldown(ApolloPlayer player, Cooldown cooldown);

    /**
     * Removes the {@link Cooldown} from the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param cooldown the cooldown
     * @since 1.0.0
     */
    public abstract void clearCooldown(ApolloPlayer player, Cooldown cooldown);

    /**
     * Clears all {@link Cooldown}s from the {@link ApolloPlayer}.
     *
     * @param player the player
     * @since 1.0.0
     */
    public abstract void clearCooldowns(ApolloPlayer player);

}
