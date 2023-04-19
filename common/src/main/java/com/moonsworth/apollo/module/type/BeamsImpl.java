package com.moonsworth.apollo.module.type;

import com.moonsworth.apollo.network.NetworkTypes;
import com.moonsworth.apollo.player.AbstractApolloPlayer;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.player.ui.Beam;
import lunarclient.apollo.common.OptionOperation;
import lunarclient.apollo.modules.BeaconBeamMessage;

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

        ((AbstractApolloPlayer) player).sendPacket(this, OptionOperation.SET, this.to(beam));
    }

    @Override
    public void removeBeam(ApolloPlayer player, Beam beam) {
        requireNonNull(player, "player");
        requireNonNull(beam, "beam");

        ((AbstractApolloPlayer) player).sendPacket(this, OptionOperation.REMOVE, this.to(beam));
    }

    @Override
    public void clearBeams(ApolloPlayer player) {
        requireNonNull(player, "player");

        ((AbstractApolloPlayer) player).sendPacket(this, OptionOperation.CLEAR);
    }

    private BeaconBeamMessage to(Beam beam) {
        return BeaconBeamMessage.newBuilder()
            .setId(beam.getId())
            .setColor(NetworkTypes.toColor(beam.getColor()))
            .setLocation(NetworkTypes.toBlockLocation(beam.getLocation()))
            .build();
    }

    private Beam from(BeaconBeamMessage message) {
        return Beam.of(
            message.getId(),
            NetworkTypes.fromColor(message.getColor()),
            NetworkTypes.fromBlockLocation(message.getLocation())
        );
    }
}
