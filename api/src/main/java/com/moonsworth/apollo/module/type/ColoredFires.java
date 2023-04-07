package com.moonsworth.apollo.module.type;

import com.moonsworth.apollo.module.ApolloModule;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.player.ui.ColoredFire;
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
     * Sends the {@link ColoredFire} to the {@link ApolloPlayer}s.
     *
     * @param fire the fire
     * @param viewers the viewers
     * @since 1.0.0
     */
    public abstract void overrideFireColor(ColoredFire fire, ApolloPlayer... viewers);


    /**
     * Resets the {@link ColoredFire} for the {@link ApolloPlayer}s.
     *
     * @param viewers the viewers
     * @since 1.0.0
     */
    public abstract void resetFireColor(ApolloPlayer... viewers);
}
