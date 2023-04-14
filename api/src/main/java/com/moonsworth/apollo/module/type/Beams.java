package com.moonsworth.apollo.module.type;

import com.moonsworth.apollo.module.ApolloModule;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.player.ui.Beam;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the beacon module.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
public abstract class Beams extends ApolloModule {

    Beams() {
        super("Beams");
    }

    /**
     * Sends the {@link Beam} to the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param beam the beam
     * @since 1.0.0
     */
    public abstract void addBeam(ApolloPlayer player, Beam beam);

    /**
     * Removes the {@link Beam} from the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param beam the beam
     * @since 1.0.0
     */
    public abstract void removeBeam(ApolloPlayer player, Beam beam);

    /**
     * Clears all {@link Beam}s from the {@link ApolloPlayer}.
     *
     * @param player the player
     * @since 1.0.0
     */
    public abstract void clearBeams(ApolloPlayer player);

}
