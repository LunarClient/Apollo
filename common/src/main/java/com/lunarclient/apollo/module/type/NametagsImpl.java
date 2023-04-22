package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.common.v1.Component;
import com.lunarclient.apollo.nametag.v1.OverrideNametagMessage;
import com.lunarclient.apollo.nametag.v1.ResetNametagMessage;
import com.lunarclient.apollo.nametag.v1.ResetNametagsMessage;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ui.Nametag;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

/**
 * Provides the nametag module.
 *
 * @since 1.0.0
 */
public final class NametagsImpl extends Nametags {

    public NametagsImpl() {
        super();
    }

    @Override
    public void overrideNametag(Nametag nametag, ApolloPlayer... viewers) {
        requireNonNull(nametag, "nametag");
        requireNonNull(viewers, "viewers");

        List<Component> lines = nametag.getNametag().stream()
            .map(NetworkTypes::toProtobuf)
            .collect(Collectors.toList());

        OverrideNametagMessage message = OverrideNametagMessage.newBuilder()
            .setPlayerUuid(NetworkTypes.toProtobuf(nametag.getPlayer()))
            .addAllLines(lines)
            .build();

        for(ApolloPlayer player : viewers) {
            ((AbstractApolloPlayer) player).sendPacket(message);
        }
    }

    @Override
    public void resetNametag(Nametag nametag, ApolloPlayer... viewers) {
        requireNonNull(nametag, "nametag");

        this.resetNametag(nametag.getPlayer(), viewers);
    }

    @Override
    public void resetNametag(UUID playerUuid, ApolloPlayer... viewers) {
        requireNonNull(playerUuid, "playerUuid");
        requireNonNull(viewers, "viewers");

        ResetNametagMessage message = ResetNametagMessage.newBuilder()
            .setPlayerUuid(NetworkTypes.toProtobuf(playerUuid))
            .build();

        for(ApolloPlayer player : viewers) {
            ((AbstractApolloPlayer) player).sendPacket(message);
        }
    }

    @Override
    public void resetNametags(ApolloPlayer player) {
        requireNonNull(player, "player");

        ((AbstractApolloPlayer) player).sendPacket(ResetNametagsMessage.getDefaultInstance());
    }
}
