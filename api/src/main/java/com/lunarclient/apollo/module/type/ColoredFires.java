package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ui.ColoredFire;
import java.util.UUID;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the colored fire module.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
public abstract class ColoredFires extends ApolloModule {

    ColoredFires() {
        super("ColoredFire");
    }

    @Override
    public boolean isClientNotify() {
        return true;
    }

    /**
     * Overrides the {@link ColoredFire} for the {@link ApolloPlayer}s.
     *
     * @param fire the fire
     * @param viewers the viewers
     * @since 1.0.0
     */
    public abstract void overrideFireColor(ColoredFire fire, ApolloPlayer... viewers);

    /**
     * Resets the {@link ColoredFire} for the {@link ApolloPlayer}s.
     *
     * @param playerUuid the player uuid
     * @param viewers the viewers
     * @since 1.0.0
     */
    public abstract void resetFireColor(UUID playerUuid, ApolloPlayer... viewers);

    /**
     * Resets the {@link ColoredFire} for the {@link ApolloPlayer}s.
     *
     * @param fire the colored fire
     * @param viewers the viewers
     * @since 1.0.0
     */
    public abstract void resetFireColor(ColoredFire fire, ApolloPlayer... viewers);

    /**
     * Resets the {@link ColoredFire} for the {@link ApolloPlayer}s.
     *
     * @param player the player
     * @param viewers the viewers
     * @since 1.0.0
     */
    public abstract void resetFireColor(ApolloPlayer player, ApolloPlayer... viewers);

    /**
     * Resets all {@link ColoredFire}s for the {@link ApolloPlayer}.
     *
     * @param player the player
     * @since 1.0.0
     */
    public abstract void resetFires(ApolloPlayer player);

}
