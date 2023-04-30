package com.lunarclient.apollo.module.beam;

import com.google.protobuf.ByteString;
import com.lunarclient.apollo.beam.v1.DisplayBeaconBeamMessage;
import com.lunarclient.apollo.beam.v1.RemoveBeaconBeamMessage;
import com.lunarclient.apollo.beam.v1.ResetBeaconBeamsMessage;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import lombok.NonNull;

/**
 * Provides the beams module.
 *
 * @since 1.0.0
 */
public final class BeamModuleImpl extends BeamModule {

    @Override
    public void displayBeam(@NonNull ApolloPlayer viewer, @NonNull Beam beam) {
        ((AbstractApolloPlayer) viewer).sendPacket(DisplayBeaconBeamMessage.newBuilder()
            .setId(ByteString.copyFromUtf8(beam.getId()))
            .setLocation(NetworkTypes.toProtobuf(beam.getLocation()))
            .setColor(NetworkTypes.toProtobuf(beam.getColor()))
            .build()
        );
    }

    @Override
    public void removeBeam(@NonNull ApolloPlayer viewer, @NonNull String beamId) {
        ((AbstractApolloPlayer) viewer).sendPacket(RemoveBeaconBeamMessage.newBuilder()
            .setId(ByteString.copyFromUtf8(beamId))
            .build()
        );
    }

    @Override
    public void removeBeam(@NonNull ApolloPlayer viewer, @NonNull Beam beam) {
        this.removeBeam(viewer, beam.getId());
    }

    @Override
    public void resetBeams(@NonNull ApolloPlayer viewer) {
        ((AbstractApolloPlayer) viewer).sendPacket(ResetBeaconBeamsMessage.getDefaultInstance());
    }

}
