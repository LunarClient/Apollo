package com.moonsworth.apollo.module.type;

import com.google.common.collect.Lists;
import com.moonsworth.apollo.option.NetworkOptions;
import com.moonsworth.apollo.option.OptionConverter;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.player.ui.Border;
import com.moonsworth.apollo.protocol.BorderMessage;

import java.awt.*;

import static java.util.Objects.requireNonNull;

/**
 * Provides the border module.
 *
 * @since 1.0.0
 */
public class BorderImpl extends Borders {

    public BorderImpl() {
        super();

        NetworkOptions.register(Border.class, BorderMessage.getDefaultInstance(), new OptionConverter<Border, BorderMessage>() {
            @Override
            public BorderMessage to(final Border object) throws IllegalArgumentException {
                return BorderMessage.newBuilder()
                    .setAction(BorderMessage.Action.valueOf(object.getAction().name()))
                    .setId(object.getId())
                    .setWorld(object.getWorld())
                    .setCancelEntry(object.isCancelEntry())
                    .setCancelExit(object.isCancelExit())
                    .setCanShrinkOrExpand(object.isCanShrinkOrExpand())
                    .setColor(object.getColor().getRGB())
                    .setMinX(object.getMinX())
                    .setMinZ(object.getMinZ())
                    .setMaxX(object.getMaxX())
                    .setMaxZ(object.getMaxZ())
                    .setDurationTicks(object.getDuration())
                    .build();
            }

            @Override
            public Border from(final BorderMessage message) throws IllegalArgumentException {
                return Border.of(
                    Border.Action.valueOf(message.getAction().name()),
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
        });
    }

    @Override
    public void addOrUpdateBorder(final ApolloPlayer player, final Border border) {
        assert border.getAction() != Border.Action.ADD_OR_UPDATE : "Invalid action: " + border.getAction();

        requireNonNull(player, "player");
        requireNonNull(border, "border");

        this.getOptions().get(player).set(Borders.BORDERS, Lists.newArrayList(border));
    }

    @Override
    public void removeBorder(final ApolloPlayer player, final Border border) {
        assert border.getAction() != Border.Action.REMOVE : "Invalid action: " + border.getAction();

        requireNonNull(player, "player");
        requireNonNull(border, "border");

        this.getOptions().get(player).remove(Borders.BORDERS, Lists.newArrayList(border));
    }
}
