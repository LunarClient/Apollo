package com.lunarclient.apollo.module.type;

import com.google.protobuf.ByteString;
import com.lunarclient.apollo.border.v1.DisplayBorderMessage;
import com.lunarclient.apollo.border.v1.RemoveBorderMessage;
import com.lunarclient.apollo.common.v1.Cuboid2D;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ui.Border;

import static java.util.Objects.requireNonNull;

/**
 * Provides the border module.
 *
 * @since 1.0.0
 */
public final class BordersImpl extends Borders {

    public BordersImpl() {
        super();
    }

    @Override
    public void addBorder(ApolloPlayer player, Border border) {
        requireNonNull(player, "player");
        requireNonNull(border, "border");

        ((AbstractApolloPlayer) player).sendPacket(DisplayBorderMessage.newBuilder()
            .setId(ByteString.copyFromUtf8(border.getId()))
            .setWorld(border.getWorld())
            .setCancelEntry(border.isCancelEntry())
            .setCancelExit(border.isCancelExit())
            .setCanShrinkOrExpand(border.isCanShrinkOrExpand())
            .setColor(NetworkTypes.toProtobuf(border.getColor()))
            .setBounds(Cuboid2D.newBuilder()
                .setMinX(border.getMinX())
                .setMinZ(border.getMinZ())
                .setMaxX(border.getMaxX())
                .setMaxZ(border.getMaxZ())
                .build()
            )
            .setDurationTicks(border.getDuration())
            .build()
        );
    }

    @Override
    public void removeBorder(ApolloPlayer player, Border border) {
        requireNonNull(player, "player");
        requireNonNull(border, "border");

        ((AbstractApolloPlayer) player).sendPacket(RemoveBorderMessage.newBuilder()
            .setId(ByteString.copyFromUtf8(border.getId()))
            .build()
        );
    }

}
