package com.lunarclient.apollo.module.type;

import com.google.protobuf.ByteString;
import com.lunarclient.apollo.border.v1.DisplayBorderMessage;
import com.lunarclient.apollo.border.v1.RemoveBorderMessage;
import com.lunarclient.apollo.border.v1.ResetBordersMessage;
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
    public void displayBorder(ApolloPlayer player, Border border) {
        requireNonNull(player, "player");
        requireNonNull(border, "border");

        ((AbstractApolloPlayer) player).sendPacket(DisplayBorderMessage.newBuilder()
            .setId(ByteString.copyFromUtf8(border.getId()))
            .setWorld(border.getWorld())
            .setCancelEntry(border.isCancelEntry())
            .setCancelExit(border.isCancelExit())
            .setCanShrinkOrExpand(border.isCanShrinkOrExpand())
            .setColor(NetworkTypes.toProtobuf(border.getColor()))
            .setBounds(NetworkTypes.toProtobuf(border.getBounds()))
            .setDurationTicks(border.getDuration())
            .build()
        );
    }

    @Override
    public void removeBorder(ApolloPlayer player, String borderId) {
        requireNonNull(player, "player");
        requireNonNull(borderId, "borderId");

        ((AbstractApolloPlayer) player).sendPacket(RemoveBorderMessage.newBuilder()
            .setId(ByteString.copyFromUtf8(borderId))
            .build()
        );
    }

    @Override
    public void removeBorder(ApolloPlayer player, Border border) {
        requireNonNull(border, "border");

        this.removeBorder(player, border.getId());
    }

    @Override
    public void resetBorders(ApolloPlayer player) {
        requireNonNull(player, "player");

        ((AbstractApolloPlayer) player).sendPacket(ResetBordersMessage.getDefaultInstance());
    }

}
