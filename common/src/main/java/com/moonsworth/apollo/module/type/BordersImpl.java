package com.moonsworth.apollo.module.type;

import com.moonsworth.apollo.player.AbstractApolloPlayer;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.player.ui.Border;
import java.awt.Color;
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
    public void addBorder(final ApolloPlayer player, final Border border) {
        requireNonNull(player, "player");
        requireNonNull(border, "border");

        ((AbstractApolloPlayer) player).sendPacket(this, OptionOperation.SET, this.to(border));
    }

    @Override
    public void removeBorder(final ApolloPlayer player, final Border border) {
        requireNonNull(player, "player");
        requireNonNull(border, "border");

        ((AbstractApolloPlayer) player).sendPacket(this, OptionOperation.REMOVE, this.to(border));
    }

    private BorderMessage to(final Border border) {
        return BorderMessage.newBuilder()
                .setId(border.getId())
                .setWorld(border.getWorld())
                .setCancelEntry(border.isCancelEntry())
                .setCancelExit(border.isCancelExit())
                .setCanShrinkOrExpand(border.isCanShrinkOrExpand())
                .setColor(border.getColor().getRGB())
                .setMinX(border.getMinX())
                .setMinZ(border.getMinZ())
                .setMaxX(border.getMaxX())
                .setMaxZ(border.getMaxZ())
                .setDurationTicks(border.getDuration())
                .build();
    }

    private Border from(final BorderMessage message) {
        return Border.of(
                message.getId(),
                message.getWorld(),
                message.getCancelEntry(),
                message.getCancelExit(),
                message.getCanShrinkOrExpand(),
                new Color(message.getColor()),
                message.getMinX(),
                message.getMinZ(),
                message.getMaxX(),
                message.getMaxZ(),
                message.getDurationTicks()
        );
    }
}
