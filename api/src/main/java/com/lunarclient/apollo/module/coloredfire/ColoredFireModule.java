package com.lunarclient.apollo.module.coloredfire;

import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.module.ModuleDefinition;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.awt.Color;
import java.util.Collection;
import java.util.UUID;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the colored fire module.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
@ModuleDefinition(id = "colored_fire", name = "Colored Fire")
public abstract class ColoredFireModule extends ApolloModule {

    @Override
    public boolean isClientNotify() {
        return true;
    }

    /**
     * Overrides the burning fire color for the burningPlayer, visible by the viewers.
     *
     * @param viewers the players who are receiving the packet
     * @param burningPlayer the UUID of the player whose burning color will be overwrote
     * @param color the new color burningPlayer should burn in.
     * @since 1.0.0
     */
    public abstract void overrideColoredFire(Collection<ApolloPlayer> viewers, UUID burningPlayer, Color color);

    /**
     * Resets the burning fire color for the burningPlayer, visible by the viewers.
     *
     * @param viewers the players who are receiving the packet
     * @param burningPlayer the UUID of the player whose burning color was overwrote
     * @since 1.0.0
     */
    public abstract void resetColoredFire(Collection<ApolloPlayer> viewers, UUID burningPlayer);

    /**
     * Resets all colored fire overrides for the given {@link ApolloPlayer}.
     *
     * @param viewer the player who is receiving the packet
     * @since 1.0.0
     */
    public abstract void resetColoredFires(ApolloPlayer viewer);

}
