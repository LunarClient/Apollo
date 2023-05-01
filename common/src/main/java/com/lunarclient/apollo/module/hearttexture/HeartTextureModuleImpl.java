package com.lunarclient.apollo.module.hearttexture;

import com.lunarclient.apollo.hearttexture.v1.HeartType;
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
    public void overrideHeartTexture(@NonNull ApolloPlayer viewer, @NonNull HeartTexture texture, int locationX) {
        ((AbstractApolloPlayer) viewer).sendPacket(OverrideHeartTextureMessage.newBuilder()
            .setHeartType(this.toProtobuf(texture))
            .setLocationX(locationX)
            .build()
        );
    }

    @Override
    public void resetHeartTexture(@NonNull ApolloPlayer viewer, @NonNull HeartTexture texture) {
        ((AbstractApolloPlayer) viewer).sendPacket(ResetHeartTextureMessage.newBuilder()
            .setHeartType(this.toProtobuf(texture))
            .build()
        );
    }

    private HeartType toProtobuf(HeartTexture texture) {
        return HeartType.forNumber(texture.ordinal() + 1);
    }

}
