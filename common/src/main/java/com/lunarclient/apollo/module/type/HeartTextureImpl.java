package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.hearttexture.v1.OverrideHeartTextureMessage;
import com.lunarclient.apollo.hearttexture.v1.ResetHeartTextureMessage;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ui.HeartTexture;

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
    public void overrideHeartTexture(ApolloPlayer player, HeartTexture heartTexture) {
        requireNonNull(player, "player");
        requireNonNull(heartTexture, "heartTexture");

        ((AbstractApolloPlayer) player).sendPacket(OverrideHeartTextureMessage.newBuilder()
            .setHeartType(null) // TODO: figure out best way to map enums
            .setLocationX(heartTexture.getLocationX())
            .build());
    }

    @Override
    public void resetHeartTexture(ApolloPlayer player) {
        requireNonNull(player, "player");

        ((AbstractApolloPlayer) player).sendPacket(ResetHeartTextureMessage.getDefaultInstance());
    }

}
