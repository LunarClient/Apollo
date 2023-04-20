package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.option.type.RenderableString;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ui.Nametag;
import com.lunarclient.apollo.player.AbstractApolloPlayer;

import java.util.List;
import java.util.stream.Collectors;
import lunarclient.apollo.common.OptionOperation;
import lunarclient.apollo.modules.NametagMessage;
import lunarclient.apollo.utility.RenderableStringMessage;

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
    public void overrideNametag(Nametag nametag, ApolloPlayer... viewers) {
        requireNonNull(nametag, "nametag");
        requireNonNull(viewers, "viewers");

        for(ApolloPlayer player : viewers) {
            ((AbstractApolloPlayer) player).sendPacket(this, OptionOperation.ADD, this.to(nametag));
        }
    }

    @Override
    public void resetNametag(ApolloPlayer... viewers) {
        requireNonNull(viewers, "viewers");

        for(ApolloPlayer player : viewers) {
            ((AbstractApolloPlayer) player).sendPacket(this, OptionOperation.CLEAR);
        }
    }

    private NametagMessage to(Nametag nametag) {
        List<RenderableStringMessage> tags = nametag.getNametag().stream()
                .map(NetworkTypes::toRenderableString)
                .collect(Collectors.toList());

        return NametagMessage.newBuilder()
                .setPlayerUuid(NetworkTypes.toUuid(nametag.getPlayer()))
                .setHide(nametag.isHide())
                .addAllNametag(tags)
                .setPlayerNameIndex(nametag.getPlayerNameIndex())
                .build();
    }

    private Nametag from(NametagMessage message) {
        List<RenderableString> nametag = message.getNametagList().stream()
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
