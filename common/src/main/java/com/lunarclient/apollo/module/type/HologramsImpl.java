package com.lunarclient.apollo.module.type;

import com.google.protobuf.ByteString;
import com.lunarclient.apollo.hologram.v1.DisplayHologramMessage;
import com.lunarclient.apollo.hologram.v1.RemoveHologramMessage;
import com.lunarclient.apollo.hologram.v1.ResetHologramsMessage;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ui.Hologram;

import java.util.UUID;

import static java.util.Objects.requireNonNull;

/**
 * Provides the hologram module.
 *
 * @since 1.0.0
 */
public final class HologramsImpl extends Holograms {

    public HologramsImpl() {
        super();
    }

    @Override
    public void displayHologram(Hologram hologram, ApolloPlayer... viewers) {
        requireNonNull(hologram, "hologram");
        requireNonNull(viewers, "viewers");

        DisplayHologramMessage message = DisplayHologramMessage.newBuilder()
            .setId(ByteString.copyFromUtf8(hologram.getId().toString()))
            .build();

        for (ApolloPlayer viewer : viewers) {
            ((AbstractApolloPlayer) viewer).sendPacket(message);
        }
    }

    @Override
    public void removeHologram(UUID hologramId, ApolloPlayer... viewers) {
        requireNonNull(hologramId, "hologramId");
        requireNonNull(viewers, "viewers");

        RemoveHologramMessage message = RemoveHologramMessage.newBuilder()
            .setId(ByteString.copyFromUtf8(hologramId.toString()))
            .build();

        for (ApolloPlayer viewer : viewers) {
            ((AbstractApolloPlayer) viewer).sendPacket(message);
        }
    }

    @Override
    public void removeHologram(Hologram hologram, ApolloPlayer... viewers) {
        requireNonNull(hologram, "hologram");

        this.removeHologram(hologram.getId(), viewers);
    }

    @Override
    public void resetHolograms(ApolloPlayer player) {
        ((AbstractApolloPlayer) player).sendPacket(ResetHologramsMessage.getDefaultInstance());
    }

}
