package com.moonsworth.apollo.module.type;

import com.google.protobuf.Any;
import com.moonsworth.apollo.option.type.RenderableString;
import com.moonsworth.apollo.player.AbstractApolloPlayer;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.player.ui.Nametag;
import com.moonsworth.apollo.util.ProtoUtils;
import lunarclient.apollo.common.MessageOperation;
import lunarclient.apollo.common.OptionOperation;
import lunarclient.apollo.modules.NametagMessage;

import java.util.List;
import java.util.stream.Collectors;

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
        final List<lunarclient.apollo.utility.RenderableString> tags = nametag.getNametag().stream()
                .map(ProtoUtils::toProtoRenderableString)
                .collect(Collectors.toList());

        return NametagMessage.newBuilder()
                .setPlayerUuid(ProtoUtils.toProtoUuid(nametag.getPlayer()))
                .setHide(nametag.isHide())
                .addAllNametag(tags)
                .setPlayerNameIndex(nametag.getPlayerNameIndex())
                .build();
    }

    private Nametag from(final NametagMessage message) {
        final List<RenderableString> nametag = message.getNametagList().stream()
                .map(ProtoUtils::fromProtoRenderableString)
                .collect(Collectors.toList());

        return Nametag.of(
                ProtoUtils.fromProtoUuid(message.getPlayerUuid()),
                message.getHide(),
                nametag,
                message.getPlayerNameIndex()
        );
    }
}
