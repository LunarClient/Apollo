package com.lunarclient.apollo.module.coloredfire;

import com.lunarclient.apollo.coloredfire.v1.OverrideColoredFireMessage;
import com.lunarclient.apollo.coloredfire.v1.ResetColoredFireMessage;
import com.lunarclient.apollo.coloredfire.v1.ResetColoredFiresMessage;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.awt.Color;
import java.util.Collection;
import java.util.UUID;
import lombok.NonNull;

/**
 * Provides the colored fire module.
 *
 * @since 1.0.0
 */
public class ColoredFireModuleImpl extends ColoredFireModule {

    @Override
    public void overrideColoredFire(@NonNull Collection<ApolloPlayer> viewers, @NonNull UUID burningPlayer, @NonNull Color color) {
        OverrideColoredFireMessage message = OverrideColoredFireMessage.newBuilder()
            .setPlayerUuid(NetworkTypes.toProtobuf(burningPlayer))
            .setColor(NetworkTypes.toProtobuf(color))
            .build();

        for (ApolloPlayer player : viewers) {
            ((AbstractApolloPlayer) player).sendPacket(message);
        }
    }

    @Override
    public void resetColoredFire(@NonNull Collection<ApolloPlayer> viewers, @NonNull UUID burningPlayer) {
        ResetColoredFireMessage message = ResetColoredFireMessage.newBuilder()
            .setPlayerUuid(NetworkTypes.toProtobuf(burningPlayer))
            .build();

        for (ApolloPlayer player : viewers) {
            ((AbstractApolloPlayer) player).sendPacket(message);
        }
    }

    @Override
    public void resetColoredFires(@NonNull ApolloPlayer viewer) {
        ((AbstractApolloPlayer) viewer).sendPacket(ResetColoredFiresMessage.getDefaultInstance());
    }

}
