package com.lunarclient.apollo.module.hearttexture;

import com.lunarclient.apollo.hearttexture.v1.OverrideHeartTextureMessage;
import com.lunarclient.apollo.hearttexture.v1.ResetHeartTextureMessage;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import lombok.NonNull;

/**
 * Provides the heart texture module.
 *
 * @since 1.0.0
 */
public final class HeartTextureModuleImpl extends HeartTextureModule {

    @Override
    public void overrideHeartTexture(@NonNull ApolloPlayer viewer, int locationX, boolean hardCore) {
        ((AbstractApolloPlayer) viewer).sendPacket(OverrideHeartTextureMessage.newBuilder()
            .setLocationX(locationX)
            // TODO
            .build()
        );
    }

    @Override
    public void resetHeartTexture(@NonNull ApolloPlayer viewer) {
        ((AbstractApolloPlayer) viewer).sendPacket(ResetHeartTextureMessage.getDefaultInstance());
    }
}
