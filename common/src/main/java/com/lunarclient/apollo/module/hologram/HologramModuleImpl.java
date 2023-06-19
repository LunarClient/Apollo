package com.lunarclient.apollo.module.hologram;

import com.google.protobuf.ByteString;
import com.lunarclient.apollo.hologram.v1.DisplayHologramMessage;
import com.lunarclient.apollo.hologram.v1.RemoveHologramMessage;
import com.lunarclient.apollo.hologram.v1.ResetHologramsMessage;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.util.Collection;
import java.util.stream.Collectors;
import lombok.NonNull;

/**
 * Provides the hologram module.
 *
 * @since 1.0.0
 */
public final class HologramModuleImpl extends HologramModule {

    @Override
    public void displayHologram(@NonNull Collection<ApolloPlayer> viewers, @NonNull Hologram hologram) {
        DisplayHologramMessage message = DisplayHologramMessage.newBuilder()
            .setId(ByteString.copyFromUtf8(hologram.getId()))
            .setLocation(NetworkTypes.toProtobuf(hologram.getLocation()))
            .addAllLines(hologram.getLines().stream()
                .map(NetworkTypes::toProtobuf)
                .collect(Collectors.toList())
            )
            .setShowThroughWalls(hologram.isShowThroughWalls())
            .setShowShadow(hologram.isShowShadow())
            .setShowBackground(hologram.isShowBackground())
            .build();

        for (ApolloPlayer viewer : viewers) {
            ((AbstractApolloPlayer) viewer).sendPacket(message);
        }
    }

    @Override
    public void removeHologram(@NonNull Collection<ApolloPlayer> viewers, @NonNull String hologramId) {
        RemoveHologramMessage message = RemoveHologramMessage.newBuilder()
            .setId(ByteString.copyFromUtf8(hologramId))
            .build();

        for (ApolloPlayer viewer : viewers) {
            ((AbstractApolloPlayer) viewer).sendPacket(message);
        }
    }

    @Override
    public void removeHologram(@NonNull Collection<ApolloPlayer> viewers, @NonNull Hologram hologram) {
        this.removeHologram(viewers, hologram.getId());
    }

    @Override
    public void resetHolograms(@NonNull ApolloPlayer viewer) {
        ((AbstractApolloPlayer) viewer).sendPacket(ResetHologramsMessage.getDefaultInstance());
    }

}
