package com.moonsworth.apollo.module.type;

import com.google.common.collect.Lists;
import com.moonsworth.apollo.option.NetworkOptions;
import com.moonsworth.apollo.option.OptionConverter;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.player.ui.Title;
import com.moonsworth.apollo.protocol.TitleMessage;

import java.time.Duration;

import static java.util.Objects.requireNonNull;

/**
 * Provides the title module.
 *
 * @since 1.0.0
 */
public final class TitlesImpl extends Titles {

    public TitlesImpl() {
        super();

        NetworkOptions.register(Title.class, TitleMessage.getDefaultInstance(), new OptionConverter<Title, TitleMessage>() {
            @Override
            public TitleMessage to(final Title object) throws IllegalArgumentException {
                return TitleMessage.newBuilder()
                    .setType(TitleMessage.Type.valueOf(object.getType().name()))
                    .setMessage(object.getMessage())
                    .setScale(object.getScale())
                    .setDisplayTime(object.getDisplayTime().toMillis())
                    .setFadeInTime(object.getFadeInTime().toMillis())
                    .setFadeOutTime(object.getFadeOutTime().toMillis())
                    .build();
            }

            @Override
            public Title from(final TitleMessage message) throws IllegalArgumentException {
                return Title.of(
                    Title.Type.valueOf(message.getType().name()),
                    message.getMessage(),
                    message.getScale(),
                    Duration.ofMillis(message.getDisplayTime()),
                    Duration.ofMillis(message.getFadeInTime()),
                    Duration.ofMillis(message.getFadeOutTime())
                );
            }
        });
    }

    @Override
    public void sendTitle(final ApolloPlayer player, final Title title) {
        requireNonNull(player, "player");
        requireNonNull(title, "title");
        this.getOptions().set(player, null, Lists.newArrayList(title));
    }

    @Override
    public void sendTitleAll(final Title title) {
        requireNonNull(title, "title");
        this.getOptions().set(null, Lists.newArrayList(title));
    }
}
