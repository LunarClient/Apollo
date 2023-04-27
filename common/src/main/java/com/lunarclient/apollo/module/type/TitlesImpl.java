package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ui.Title;
import com.lunarclient.apollo.title.v1.DisplayTitleMessage;
import com.lunarclient.apollo.title.v1.ResetTitlesMessage;
import com.lunarclient.apollo.title.v1.TitleType;

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
    public void displayTitleMessage(ApolloPlayer player, Title title) {
        requireNonNull(player, "player");
        requireNonNull(title, "title");

        ((AbstractApolloPlayer) player).sendPacket(DisplayTitleMessage.newBuilder()
            .setTitleType(TitleType.forNumber(title.getType().ordinal() + 1))
            .setMessage(NetworkTypes.toProtobuf(title.getMessage()))
            .setScale(title.getScale())
            .setFadeInTime(NetworkTypes.toProtobuf(title.getFadeInTime()))
            .setDisplayTime(NetworkTypes.toProtobuf(title.getDisplayTime()))
            .setFadeOutTime(NetworkTypes.toProtobuf(title.getFadeOutTime()))
            .build());
    }

    @Override
    public void broadcastTitle(Title title) {
        requireNonNull(title, "title");

        for(ApolloPlayer player : Apollo.getPlayerManager().getPlayers()) {
            this.displayTitleMessage(player, title);
        }
    }

    @Override
    public void resetTitles(ApolloPlayer player) {
        requireNonNull(player, "player");

        ((AbstractApolloPlayer) player).sendPacket(ResetTitlesMessage.getDefaultInstance());
    }

}
