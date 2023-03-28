package com.moonsworth.apollo.module.type;

import com.google.common.collect.Lists;
import com.moonsworth.apollo.option.NetworkOptions;
import com.moonsworth.apollo.option.OptionConverter;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.player.ui.HeartTexture;
import com.moonsworth.apollo.protocol.HeartTextureMessage;

import static java.util.Objects.requireNonNull;

/**
 * Provides the heart texture module.
 *
 * @since 1.0.0
 */
public final class HeartTextureImpl extends HeartTextures {

    public HeartTextureImpl() {
        super();

        NetworkOptions.register(HeartTexture.class, HeartTextureMessage.getDefaultInstance(), new OptionConverter<HeartTexture, HeartTextureMessage>() {
            @Override
            public HeartTextureMessage to(final HeartTexture object) throws IllegalArgumentException {
                return HeartTextureMessage.newBuilder()
                    .setType(HeartTextureMessage.Type.valueOf(object.getType().name()))
                    .setLocationX(object.getLocationX())
                    .build();
            }

            @Override
            public HeartTexture from(final HeartTextureMessage message) throws IllegalArgumentException {
                return HeartTexture.of(
                    HeartTexture.Type.valueOf(message.getType().name()),
                    message.getLocationX()
                );
            }
        });
    }

    @Override
    public void sendHeartTexture(final ApolloPlayer player, final HeartTextures heartTexture) {
        requireNonNull(player, "player");
        requireNonNull(heartTexture, "heartTexture");
        this.getOptions().set(player, HeartTextures.HEART_TEXTURES, Lists.newArrayList(heartTexture));
    }

    @Override
    public void removeHeartTexture(final ApolloPlayer player) {
        requireNonNull(player, "player");
        this.getOptions().remove(player, HeartTextures.HEART_TEXTURES, Lists.newArrayList());
    }
}
