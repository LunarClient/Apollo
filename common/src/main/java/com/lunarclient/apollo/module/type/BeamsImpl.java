package com.lunarclient.apollo.module.type;

import com.google.protobuf.ByteString;
import com.lunarclient.apollo.beam.v1.DisplayBeaconBeamMessage;
import com.lunarclient.apollo.beam.v1.RemoveBeaconBeamMessage;
import com.lunarclient.apollo.beam.v1.ResetBeaconBeamsMessage;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ui.Beam;

import static java.util.Objects.requireNonNull;

/**
 * Provides the beams module.
 *
 * @since 1.0.0
 */
public final class BeamsImpl extends Beams {

    public BeamsImpl() {
        super();
    }

    @Override
    public void addBeam(ApolloPlayer player, Beam beam) {
        requireNonNull(player, "player");
        requireNonNull(beam, "beam");

        ((AbstractApolloPlayer) player).sendPacket(DisplayBeaconBeamMessage.newBuilder()
            .setId(ByteString.copyFromUtf8(beam.getId()))
            .setLocation(NetworkTypes.toProtobuf(beam.getLocation()))
            .setColor(NetworkTypes.toProtobuf(beam.getColor()))
            .build()
        );
    }

    @Override
    public void removeBeam(ApolloPlayer player, Beam beam) {
        requireNonNull(player, "player");
        requireNonNull(beam, "beam");

        ((AbstractApolloPlayer) player).sendPacket(RemoveBeaconBeamMessage.newBuilder()
            .setId(ByteString.copyFromUtf8(beam.getId()))
            .build()
        );
    }

    @Override
    public void clearBeams(ApolloPlayer player) {
        requireNonNull(player, "player");

        ((AbstractApolloPlayer) player).sendPacket(ResetBeaconBeamsMessage.getDefaultInstance());
    }

}
