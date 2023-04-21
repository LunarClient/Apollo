package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ui.Title;
import lunarclient.apollo.common.OptionOperation;
import lunarclient.apollo.modules.TitleMessage;

import static java.util.Objects.requireNonNull;

/**
 * Provides the title module.
 *
 * @since 1.0.0
 */
public final class TitlesImpl extends Titles {

    public TitlesImpl() {
        super();
    }

    @Override
    public void sendTitle(ApolloPlayer player, Title title) {
        requireNonNull(player, "player");
        requireNonNull(title, "title");

        ((AbstractApolloPlayer) player).sendPacket(this, OptionOperation.ADD, this.to(title));
    }

    @Override
    public void broadcastTitle(Title title) {
        requireNonNull(title, "title");

        for(ApolloPlayer player : Apollo.getPlayerManager().getPlayers()) {
            ((AbstractApolloPlayer) player).sendPacket(this, OptionOperation.ADD, this.to(title));
        }
    }

    private TitleMessage to(Title title) {
        return TitleMessage.newBuilder()
                .setType(TitleMessage.Type.valueOf(title.getType().name()))
                .setMessage(title.getMessage())
                .setScale(title.getScale())
                .setDisplayTime(NetworkTypes.toProtobuf(title.getDisplayTime()))
                .setFadeInTime(NetworkTypes.toProtobuf(title.getFadeInTime()))
                .setFadeOutTime(NetworkTypes.toProtobuf(title.getFadeOutTime()))
                .build();
    }

    private Title from(TitleMessage message) {
        return Title.of(
                Title.Type.valueOf(message.getType().name()),
                message.getMessage(),
                message.getScale(),
                NetworkTypes.fromProtobuf(message.getDisplayTime()),
                NetworkTypes.fromProtobuf(message.getFadeInTime()),
                NetworkTypes.fromProtobuf(message.getFadeOutTime())
        );
    }
}
