package com.lunarclient.apollo.module.nametag;

import com.lunarclient.apollo.common.v1.Component;
import com.lunarclient.apollo.nametag.v1.OverrideNametagMessage;
import com.lunarclient.apollo.nametag.v1.ResetNametagMessage;
import com.lunarclient.apollo.nametag.v1.ResetNametagsMessage;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.NonNull;

/**
 * Provides the nametag module.
 *
 * @since 1.0.0
 */
public final class NametagModuleImpl extends NametagModule {

    @Override
    public void overrideNametag(@NonNull Collection<ApolloPlayer> viewers, @NonNull UUID playerUuid, @NonNull Nametag nametag) {
        List<Component> lines = nametag.getLines().stream()
            .map(NetworkTypes::toProtobuf)
            .collect(Collectors.toList());

        OverrideNametagMessage message = OverrideNametagMessage.newBuilder()
            .setPlayerUuid(NetworkTypes.toProtobuf(playerUuid))
            .addAllLines(lines)
            .build();

        for (ApolloPlayer player : viewers) {
            ((AbstractApolloPlayer) player).sendPacket(message);
        }
    }

    @Override
    public void resetNametag(@NonNull Collection<ApolloPlayer> viewers, @NonNull UUID playerUuid) {
        ResetNametagMessage message = ResetNametagMessage.newBuilder()
            .setPlayerUuid(NetworkTypes.toProtobuf(playerUuid))
            .build();

        for (ApolloPlayer player : viewers) {
            ((AbstractApolloPlayer) player).sendPacket(message);
        }
    }

    @Override
    public void resetNametags(@NonNull ApolloPlayer player) {
        ((AbstractApolloPlayer) player).sendPacket(ResetNametagsMessage.getDefaultInstance());
    }

}
