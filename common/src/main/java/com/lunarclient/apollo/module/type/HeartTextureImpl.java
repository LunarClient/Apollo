package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ui.HeartTexture;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
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
    public void sendHeartTexture(ApolloPlayer player, HeartTexture heartTexture) {
        requireNonNull(player, "player");
        requireNonNull(heartTexture, "heartTexture");

        ((AbstractApolloPlayer) player).sendPacket(this, OptionOperation.SET, this.to(heartTexture));
    }

    @Override
    public void removeHeartTexture(ApolloPlayer player) {
        requireNonNull(player, "player");

        ((AbstractApolloPlayer) player).sendPacket(this, OptionOperation.CLEAR);
    }

    private HeartTextureMessage to(HeartTexture texture) {
        return HeartTextureMessage.newBuilder()
                .setType(HeartTextureMessage.Type.valueOf(texture.getType().name()))
                .setLocationX(texture.getLocationX())
                .build();
    }

    private HeartTexture from(HeartTextureMessage message) {
        return HeartTexture.of(
                HeartTexture.Type.valueOf(message.getType().name()),
                message.getLocationX()
        );
    }

}
