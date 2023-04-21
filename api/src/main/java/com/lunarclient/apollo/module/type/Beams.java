package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ui.Beam;
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
     * Displays the {@link Beam} for the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param beam the beam
     * @since 1.0.0
     */
    public abstract void displayBeam(ApolloPlayer player, Beam beam);

    /**
     * Removes the {@link Beam} from the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param beamId the beam id
     * @since 1.0.0
     */
    public abstract void removeBeam(ApolloPlayer player, String beamId);

    /**
     * Removes the {@link Beam} from the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param beam the beam
     * @since 1.0.0
     */
    public abstract void removeBeam(ApolloPlayer player, Beam beam);

    /**
     * Resets all {@link Beam}s for the {@link ApolloPlayer}.
     *
     * @param player the player
     * @since 1.0.0
     */
    public abstract void resetBeams(ApolloPlayer player);

}
