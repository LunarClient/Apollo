package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ui.HeartTexture;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the heart texture module.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
public abstract class HeartTextures extends ApolloModule {

    HeartTextures() {
        super("HeartTexture");
    }

    @Override
    public boolean isClientNotify() {
        return true;
    }

    /**
     * Sends the {@link HeartTextures} to the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param heartTexture the heart texture
     * @since 1.0.0
     */
    public abstract void sendHeartTexture(ApolloPlayer player, HeartTexture heartTexture);

    /**
     * Removes the {@link HeartTextures} from the {@link ApolloPlayer}.
     *
     * @param player the player
     * @since 1.0.0
     */
    public abstract void removeHeartTexture(ApolloPlayer player);
}