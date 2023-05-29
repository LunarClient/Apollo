package com.lunarclient.apollo.module.misc;

import com.lunarclient.apollo.common.ApolloEntity;
import com.lunarclient.apollo.common.v1.EntityId;
import com.lunarclient.apollo.misc.v1.DisplayVignetteMessage;
import com.lunarclient.apollo.misc.v1.FlipEntityMessage;
import com.lunarclient.apollo.misc.v1.OverrideRainbowSheepMessage;
import com.lunarclient.apollo.misc.v1.ResetFlipedEntityMessage;
import com.lunarclient.apollo.misc.v1.ResetRainbowSheepMessage;
import com.lunarclient.apollo.misc.v1.ResetVignetteMessage;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.NonNull;

/**
 * Provides the misc module.
 *
 * @since 1.0.0
 */
public final class MiscModuleImpl extends MiscModule {

    @Override
    public void displayVignette(@NonNull ApolloPlayer viewer, @NonNull Vignette vignette) {
        ((AbstractApolloPlayer) viewer).sendPacket(DisplayVignetteMessage.newBuilder()
            .setResourceLocation(vignette.getResourceLocation())
            .setOpacity(vignette.getOpacity())
            .build());
    }

    @Override
    public void resetVignette(@NonNull ApolloPlayer viewer) {
        ((AbstractApolloPlayer) viewer).sendPacket(ResetVignetteMessage.getDefaultInstance());
    }

    @Override
    public void overrideRainbowSheep(@NonNull ApolloPlayer viewer, @NonNull List<ApolloEntity> sheepEntities) {
        Set<EntityId> sheepUuidsProto = sheepEntities.stream()
            .map(entity -> NetworkTypes.toProtobuf(viewer, entity))
            .collect(Collectors.toSet());

        ((AbstractApolloPlayer) viewer).sendPacket(OverrideRainbowSheepMessage.newBuilder()
            .addAllEntityIds(sheepUuidsProto)
            .build());
    }

    @Override
    public void resetRainbowSheep(@NonNull ApolloPlayer viewer, @NonNull List<ApolloEntity> sheepEntities) {
        Set<EntityId> sheepUuidsProto = sheepEntities.stream()
            .map(entity -> NetworkTypes.toProtobuf(viewer, entity))
            .collect(Collectors.toSet());

        ((AbstractApolloPlayer) viewer).sendPacket(ResetRainbowSheepMessage.newBuilder()
            .addAllEntityIds(sheepUuidsProto)
            .build());
    }

    @Override
    public void flipEntity(@NonNull ApolloPlayer viewer, @NonNull List<ApolloEntity> entities) {
        Set<EntityId> entityUuidsProto = entities.stream()
            .map(entity -> NetworkTypes.toProtobuf(viewer, entity))
            .collect(Collectors.toSet());

        ((AbstractApolloPlayer) viewer).sendPacket(FlipEntityMessage.newBuilder()
            .addAllEntityIds(entityUuidsProto)
            .build());
    }

    @Override
    public void resetFlippedEntity(@NonNull ApolloPlayer viewer, @NonNull List<ApolloEntity> entities) {
        Set<EntityId> entityUuidsProto = entities.stream()
            .map(entity -> NetworkTypes.toProtobuf(viewer, entity))
            .collect(Collectors.toSet());

        ((AbstractApolloPlayer) viewer).sendPacket(ResetFlipedEntityMessage.newBuilder()
            .addAllEntityIds(entityUuidsProto)
            .build());
    }

}
