package com.moonsworth.apollo.module.type;

import com.google.protobuf.Any;
import com.moonsworth.apollo.Apollo;
import com.moonsworth.apollo.player.AbstractApolloPlayer;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.player.ui.Title;
import com.moonsworth.apollo.util.ProtoUtils;
import lunarclient.apollo.common.MessageOperation;
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
    public void sendTitle(final ApolloPlayer player, final Title title) {
        requireNonNull(player, "player");
        requireNonNull(title, "title");

        ((AbstractApolloPlayer) player).sendPacket(MessageOperation.newBuilder()
                .setModule(this.getName())
                .setOperation(OptionOperation.ADD)
                .setValue(Any.pack(this.to(title)))
                .build());
    }

    @Override
    public void broadcastTitle(final Title title) {
        requireNonNull(title, "title");

        for(final ApolloPlayer player : Apollo.getPlayerManager().getPlayers()) {
            ((AbstractApolloPlayer) player).sendPacket(MessageOperation.newBuilder()
                    .setModule(this.getName())
                    .setOperation(OptionOperation.ADD)
                    .setValue(Any.pack(this.to(title)))
                    .build());
        }
    }

    private TitleMessage to(final Title title) {
        return TitleMessage.newBuilder()
                .setType(TitleMessage.Type.valueOf(title.getType().name()))
                .setMessage(title.getMessage())
                .setScale(title.getScale())
                .setDisplayTime(ProtoUtils.toProtoDuration(title.getDisplayTime()))
                .setFadeInTime(ProtoUtils.toProtoDuration(title.getFadeInTime()))
                .setFadeOutTime(ProtoUtils.toProtoDuration(title.getFadeOutTime()))
                .build();
    }

    private Title from(final TitleMessage message) {
        return Title.of(
                Title.Type.valueOf(message.getType().name()),
                message.getMessage(),
                message.getScale(),
                ProtoUtils.fromProtoDuration(message.getDisplayTime()),
                ProtoUtils.fromProtoDuration(message.getFadeInTime()),
                ProtoUtils.fromProtoDuration(message.getFadeOutTime())
        );
    }
}
