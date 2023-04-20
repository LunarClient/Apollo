package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ui.Border;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import lunarclient.apollo.common.OptionOperation;
import lunarclient.apollo.modules.BorderMessage;

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

        ((AbstractApolloPlayer) player).sendPacket(this, OptionOperation.SET, this.to(border));
    }

    @Override
    public void removeBorder(ApolloPlayer player, Border border) {
        requireNonNull(player, "player");
        requireNonNull(border, "border");

        ((AbstractApolloPlayer) player).sendPacket(this, OptionOperation.REMOVE, this.to(border));
    }

    private BorderMessage to(Border border) {
        return BorderMessage.newBuilder()
                .setId(border.getId())
                .setWorld(border.getWorld())
                .setCancelEntry(border.isCancelEntry())
                .setCancelExit(border.isCancelExit())
                .setCanShrinkOrExpand(border.isCanShrinkOrExpand())
                .setColor(NetworkTypes.toColor(border.getColor()))
                .setMinX(border.getMinX())
                .setMinZ(border.getMinZ())
                .setMaxX(border.getMaxX())
                .setMaxZ(border.getMaxZ())
                .setDurationTicks(border.getDuration())
                .build();
    }

    private Border from(BorderMessage message) {
        return Border.of(
                message.getId(),
                message.getWorld(),
                message.getCancelEntry(),
                message.getCancelExit(),
                message.getCanShrinkOrExpand(),
                NetworkTypes.fromColor(message.getColor()),
                message.getMinX(),
                message.getMinZ(),
                message.getMaxX(),
                message.getMaxZ(),
                message.getDurationTicks()
        );
    }
}
