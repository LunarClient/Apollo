package com.moonsworth.apollo.module.type;

import com.google.protobuf.Any;
import com.moonsworth.apollo.player.AbstractApolloPlayer;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.player.ui.Nametag;
import java.util.UUID;
import lunarclient.apollo.common.MessageOperation;
import lunarclient.apollo.common.OptionOperation;
import lunarclient.apollo.modules.NametagMessage;

import static java.util.Objects.requireNonNull;

/**
 * Provides the nametag module.
 *
 * @since 1.0.0
 */
public final class NametagsImpl extends Nametags {

    public NametagsImpl() {
        super();
    }

    @Override
    public void sendNametag(final Nametag nametag, final ApolloPlayer... viewers) {
        requireNonNull(nametag, "nametag");
        requireNonNull(viewers, "viewers");

        for(final ApolloPlayer player : viewers) {
            ((AbstractApolloPlayer) player).sendPacket(MessageOperation.newBuilder()
                    .setModule(this.getName())
                    .setOperation(OptionOperation.ADD)
                    .setValue(Any.pack(this.to(nametag)))
                    .build());
        }
    }

    @Override
    public void resetNametag(final Nametag nametag, final ApolloPlayer... viewers) {
        requireNonNull(nametag, "nametag");
        requireNonNull(viewers, "viewers");

        for(final ApolloPlayer player : viewers) {
            ((AbstractApolloPlayer) player).sendPacket(MessageOperation.newBuilder()
                    .setModule(this.getName())
                    .setOperation(OptionOperation.REMOVE)
                    .setValue(Any.pack(this.to(nametag)))
                    .build());
        }
    }


    private NametagMessage to(final Nametag nametag) {
//        final List<RenderableString> renderableStrings = nametag.getNametag().stream()
//                .map(renderableString -> renderableStringConverter.to(renderableString))
//                .collect(Collectors.toList());

        return NametagMessage.newBuilder()
                .setPlayer(nametag.getPlayer().toString())
                .setHide(nametag.isHide())
//                .addAllNametag(nametag)
                .setPlayerNameIndex(nametag.getPlayerNameIndex())
                .build();
    }

    private Nametag from(final NametagMessage message) {
//        final List<com.moonsworth.apollo.option.type.RenderableString> nametag = message.getNametagList().stream()
//                .map(renderableString -> renderableStringConverter.from(renderableString))
//                .collect(Collectors.toList());

        return Nametag.of(
                UUID.fromString(message.getPlayer()),
                message.getHide(),
//                nametag,
                null,
                message.getPlayerNameIndex()
        );
    }
}
