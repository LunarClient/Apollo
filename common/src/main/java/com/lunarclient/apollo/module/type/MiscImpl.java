package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.common.v1.Uuid;
import com.lunarclient.apollo.misc.v1.DisplayVignetteMessage;
import com.lunarclient.apollo.misc.v1.FlipEntityMessage;
import com.lunarclient.apollo.misc.v1.OverrideRainbowSheepMessage;
import com.lunarclient.apollo.misc.v1.ResetFlipedEntityMessage;
import com.lunarclient.apollo.misc.v1.ResetRainbowSheepMessage;
import com.lunarclient.apollo.misc.v1.ResetVignetteMessage;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ui.misc.Entity;
import com.lunarclient.apollo.player.ui.misc.Vignette;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

/**
 * Provides the misc module.
 *
 * @since 1.0.0
 */
public final class MiscImpl extends Misc {

    public MiscImpl() {
        super();
    }

    @Override
    public void displayVignette(ApolloPlayer player, Vignette vignette) {
        requireNonNull(player, "player");
        requireNonNull(vignette, "vignette");

        ((AbstractApolloPlayer) player).sendPacket(DisplayVignetteMessage.newBuilder()
            .setResourceLocation(vignette.getResourceLocation())
            .setOpacity(vignette.getOpacity())
            .build());
    }

    @Override
    public void resetVignette(ApolloPlayer player) {
        requireNonNull(player, "player");

        ((AbstractApolloPlayer) player).sendPacket(ResetVignetteMessage.getDefaultInstance());
    }

    @Override
    public void overrideRainbowSheep(ApolloPlayer player, Entity... sheep) {
        requireNonNull(player, "player");
        requireNonNull(sheep, "sheep");

        Set<Uuid> entityIds = Arrays.stream(sheep)
            .map(entity -> NetworkTypes.toProtobuf(entity.getId()))
            .collect(Collectors.toSet());

        ((AbstractApolloPlayer) player).sendPacket(OverrideRainbowSheepMessage.newBuilder()
            .addAllEntityIds(entityIds)
            .build());
    }

    @Override
    public void resetRainbowSheep(ApolloPlayer player, Entity... sheep) {
        requireNonNull(player, "player");
        requireNonNull(sheep, "sheep");

        Set<Uuid> entityIds = Arrays.stream(sheep)
            .map(entity -> NetworkTypes.toProtobuf(entity.getId()))
            .collect(Collectors.toSet());

        ((AbstractApolloPlayer) player).sendPacket(ResetRainbowSheepMessage.newBuilder()
            .addAllEntityIds(entityIds)
            .build());
    }

    @Override
    public void flipEntity(ApolloPlayer player, Entity... entities) {
        requireNonNull(player, "player");
        requireNonNull(entities, "entities");

        Set<Uuid> entityIds = Arrays.stream(entities)
            .map(entity -> NetworkTypes.toProtobuf(entity.getId()))
            .collect(Collectors.toSet());

        ((AbstractApolloPlayer) player).sendPacket(FlipEntityMessage.newBuilder()
            .addAllEntityIds(entityIds)
            .build());
    }

    @Override
    public void resetFlippedEntity(ApolloPlayer player, Entity... entities) {
        requireNonNull(player, "player");
        requireNonNull(entities, "entities");

        Set<Uuid> entityIds = Arrays.stream(entities)
            .map(entity -> NetworkTypes.toProtobuf(entity.getId()))
            .collect(Collectors.toSet());

        ((AbstractApolloPlayer) player).sendPacket(ResetFlipedEntityMessage.newBuilder()
            .addAllEntityIds(entityIds)
            .build());
    }

}
