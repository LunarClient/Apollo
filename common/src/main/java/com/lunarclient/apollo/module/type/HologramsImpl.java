package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ui.Hologram;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.player.AbstractApolloPlayer;

import java.util.List;
import java.util.stream.Collectors;
import lunarclient.apollo.common.OptionOperation;
import lunarclient.apollo.modules.HologramMessage;
import lunarclient.apollo.utility.RenderableStringMessage;

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
    public void addOrUpdateHologram(Hologram hologram, ApolloPlayer... viewers) {
        requireNonNull(hologram, "hologram");
        requireNonNull(viewers, "viewers");

        HologramMessage message = this.to(hologram);
        for(ApolloPlayer viewer : viewers) {
            ((AbstractApolloPlayer) viewer).sendPacket(this, OptionOperation.ADD, message);
        }
    }

    @Override
    public void removeHologram(Hologram hologram, ApolloPlayer... viewers) {
        requireNonNull(hologram, "hologram");
        requireNonNull(viewers, "viewers");

        HologramMessage message = this.to(hologram);
        for(ApolloPlayer viewer : viewers) {
            ((AbstractApolloPlayer) viewer).sendPacket(this, OptionOperation.REMOVE, message);
        }
    }

    private HologramMessage to(Hologram hologram) {
        List<RenderableStringMessage> lines = hologram.getLines().stream()
            .map(NetworkTypes::toRenderableString)
            .collect(Collectors.toList());

        return HologramMessage.newBuilder()
            .setHologramUuid(NetworkTypes.toUuid(hologram.getId()))
            .setLocation(NetworkTypes.toLocation(hologram.getLocation()))
            .addAllLines(lines)
            .build();
    }
}
