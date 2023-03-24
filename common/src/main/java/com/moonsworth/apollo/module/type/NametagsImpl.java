package com.moonsworth.apollo.module.type;

import com.google.protobuf.Message;
import com.moonsworth.apollo.option.NetworkOptions;
import com.moonsworth.apollo.option.OptionConverter;
import com.moonsworth.apollo.option.type.RenderableString;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.player.ui.Nametag;
import com.moonsworth.apollo.protocol.NametagMessage;
import com.moonsworth.apollo.protocol.RenderableStringMessage;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

/**
 * Provides the nametag module.
 *
 * @since 1.0.0
 */
public class NametagsImpl extends Nametags {

    public NametagsImpl() {
        super();

        NetworkOptions.register(Nametag.class, NametagMessage.getDefaultInstance(), new OptionConverter<Nametag, NametagMessage>() {
            @Override
            public NametagMessage to(final Nametag object) throws IllegalArgumentException {
                OptionConverter<Object, Message> renderableStringConverter = NetworkOptions.get(RenderableString.class);
                List<RenderableStringMessage> nametag = object.getNametag().stream()
                    .map(renderableString -> (RenderableStringMessage) renderableStringConverter.to(renderableString))
                    .collect(Collectors.toList());

                return NametagMessage.newBuilder()
                    .setPlayer(object.getPlayer().toString())
                    .setHide(object.isHide())
                    .addAllNametag(nametag)
                    .setPlayerNameIndex(object.getPlayerNameIndex())
                    .build();
            }

            @Override
            public Nametag from(final NametagMessage message) throws IllegalArgumentException {
                OptionConverter<Object, Message> renderableStringConverter = NetworkOptions.get(RenderableString.class);
                List<RenderableString> nametag = message.getNametagList().stream()
                    .map(renderableString -> (RenderableString) renderableStringConverter.from(renderableString))
                    .collect(Collectors.toList());

                return Nametag.of(
                    UUID.fromString(message.getPlayer()),
                    message.getHide(),
                    nametag,
                    message.getPlayerNameIndex()
                );
            }
        });
    }

    @Override
    public void sendNametag(final Nametag nametag, final ApolloPlayer... viewers) {
        requireNonNull(nametag, "nametag");
        requireNonNull(viewers, "viewers");
        // TODO
    }

    @Override
    public void resetNametag(final Nametag nametag, final ApolloPlayer... viewers) {
        requireNonNull(nametag, "nametag");
        requireNonNull(viewers, "viewers");
        // TODO
    }
}
