package com.moonsworth.apollo.module.type;

import com.google.protobuf.Any;
import com.moonsworth.apollo.player.AbstractApolloPlayer;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.player.ui.HeartTexture;
import lunarclient.apollo.common.MessageOperation;
import lunarclient.apollo.common.OptionOperation;
import lunarclient.apollo.modules.HeartTextureMessage;

import static java.util.Objects.requireNonNull;

/**
 * Provides the heart texture module.
 *
 * @since 1.0.0
 */
public final class HeartTextureImpl extends HeartTextures {

    public HeartTextureImpl() {
        super();
    }

    @Override
    public void sendHeartTexture(final ApolloPlayer player, final HeartTexture heartTexture) {
        requireNonNull(player, "player");
        requireNonNull(heartTexture, "heartTexture");

        ((AbstractApolloPlayer) player).sendPacket(MessageOperation.newBuilder()
                .setModule(this.getName())
                .setOperation(OptionOperation.SET)
                .setValue(Any.pack(this.to(heartTexture)))
                .build());
    }

    @Override
    public void removeHeartTexture(final ApolloPlayer player) {
        requireNonNull(player, "player");

        ((AbstractApolloPlayer) player).sendPacket(MessageOperation.newBuilder()
                .setModule(this.getName())
                .setOperation(OptionOperation.CLEAR)
                .build());
    }

    private HeartTextureMessage to(final HeartTexture texture) {
        return HeartTextureMessage.newBuilder()
                .setType(HeartTextureMessage.Type.valueOf(texture.getType().name()))
                .setLocationX(texture.getLocationX())
                .build();
    }

    private HeartTexture from(final HeartTextureMessage message) {
        return HeartTexture.of(
                HeartTexture.Type.valueOf(message.getType().name()),
                message.getLocationX()
        );
    }

}
