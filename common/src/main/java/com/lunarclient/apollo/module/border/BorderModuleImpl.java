package com.lunarclient.apollo.module.border;

import com.google.protobuf.ByteString;
import com.lunarclient.apollo.border.v1.DisplayBorderMessage;
import com.lunarclient.apollo.border.v1.RemoveBorderMessage;
import com.lunarclient.apollo.border.v1.ResetBordersMessage;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import lombok.NonNull;

/**
 * Provides the border module.
 *
 * @since 1.0.0
 */
public final class BorderModuleImpl extends BorderModule {

    @Override
    public void displayBorder(@NonNull ApolloPlayer viewer, @NonNull Border border) {
        ((AbstractApolloPlayer) viewer).sendPacket(DisplayBorderMessage.newBuilder()
            .setId(ByteString.copyFromUtf8(border.getId()))
            .setWorld(border.getWorld())
            .setCancelEntry(border.isCancelEntry())
            .setCancelExit(border.isCancelExit())
            .setCanShrinkOrExpand(border.isCanShrinkOrExpand())
            .setColor(NetworkTypes.toProtobuf(border.getColor()))
            .setBounds(NetworkTypes.toProtobuf(border.getBounds()))
            .setDurationTicks(border.getDurationTicks())
            .build()
        );
    }

    @Override
    public void removeBorder(@NonNull ApolloPlayer viewer, @NonNull String borderId) {
        ((AbstractApolloPlayer) viewer).sendPacket(RemoveBorderMessage.newBuilder()
            .setId(ByteString.copyFromUtf8(borderId))
            .build()
        );
    }

    @Override
    public void removeBorder(@NonNull ApolloPlayer viewer, @NonNull Border border) {
        this.removeBorder(viewer, border.getId());
    }

    @Override
    public void resetBorders(@NonNull ApolloPlayer viewer) {
        ((AbstractApolloPlayer) viewer).sendPacket(ResetBordersMessage.getDefaultInstance());
    }

}
