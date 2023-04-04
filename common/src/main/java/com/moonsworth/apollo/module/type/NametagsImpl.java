package com.moonsworth.apollo.module.type;

import com.moonsworth.apollo.network.NetworkTypes;
import com.moonsworth.apollo.option.type.RenderableString;
import com.moonsworth.apollo.player.AbstractApolloPlayer;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.player.ui.Nametag;
import java.util.List;
import java.util.stream.Collectors;
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
    public void overrideNametag(final Nametag nametag, final ApolloPlayer... viewers) {
        requireNonNull(nametag, "nametag");
        requireNonNull(viewers, "viewers");

        for(final ApolloPlayer player : viewers) {
            ((AbstractApolloPlayer) player).sendPacket(this, OptionOperation.ADD, this.to(nametag));
        }
    }

    @Override
    public void resetNametag(final Nametag nametag, final ApolloPlayer... viewers) {
        requireNonNull(nametag, "nametag");
        requireNonNull(viewers, "viewers");

        for(final ApolloPlayer player : viewers) {
            ((AbstractApolloPlayer) player).sendPacket(this, OptionOperation.REMOVE, this.to(nametag));
        }
    }


    private NametagMessage to(final Nametag nametag) {
        final List<lunarclient.apollo.utility.RenderableString> tags = nametag.getNametag().stream()
                .map(NetworkTypes::toRenderableString)
                .collect(Collectors.toList());

        return NametagMessage.newBuilder()
                .setPlayerUuid(NetworkTypes.toUuid(nametag.getPlayer()))
                .setHide(nametag.isHide())
                .addAllNametag(tags)
                .setPlayerNameIndex(nametag.getPlayerNameIndex())
                .build();
    }

    private Nametag from(final NametagMessage message) {
        final List<RenderableString> nametag = message.getNametagList().stream()
                .map(NetworkTypes::fromRenderableString)
                .collect(Collectors.toList());

        return Nametag.of(
                NetworkTypes.fromUuid(message.getPlayerUuid()),
                message.getHide(),
                nametag,
                message.getPlayerNameIndex()
        );
    }
}
