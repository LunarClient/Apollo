package com.lunarclient.apollo.module.beam;

import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the beam module.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
public abstract class BeamModule extends ApolloModule {

    BeamModule() {
        super("Beam");
    }

    /**
     * Displays the {@link Beam} to the {@link ApolloPlayer}.
     *
     * @param viewer the player who is receiving the packet
     * @param beam the beam
     * @since 1.0.0
     */
    public abstract void displayBeam(ApolloPlayer viewer, Beam beam);

    /**
     * Removes the {@link Beam} from the {@link ApolloPlayer}.
     *
     * @param viewer the player who is receiving the packet
     * @param beamId the beam id
     * @since 1.0.0
     */
    public abstract void removeBeam(ApolloPlayer viewer, String beamId);

    /**
     * Removes the {@link Beam} from the {@link ApolloPlayer}.
     *
     * @param viewer the player who is receiving the packet
     * @param beam the beam
     * @since 1.0.0
     */
    public abstract void removeBeam(ApolloPlayer viewer, Beam beam);

    /**
     * Resets all {@link Beam}s for the {@link ApolloPlayer}.
     *
     * @param viewer the player who is receiving the packet
     * @since 1.0.0
     */
    public abstract void resetBeams(ApolloPlayer viewer);

}
