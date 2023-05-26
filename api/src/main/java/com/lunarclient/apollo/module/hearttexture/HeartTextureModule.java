package com.lunarclient.apollo.module.hearttexture;

import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.module.ModuleDefinition;
import com.lunarclient.apollo.player.ApolloPlayer;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the heart texture module.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
@ModuleDefinition(id = "heart_texture", name = "Heart Texture")
public abstract class HeartTextureModule extends ApolloModule {

    @Override
    public boolean isClientNotify() {
        return true;
    }

    /**
     * Overrides a heart texture for the {@link ApolloPlayer}.
     *
     * @param viewer the player who is receiving the packet
     * @param locationX X offset of new heart texture in a custom resource pack
     * @since 1.0.0
     */
    public abstract void overrideHeartTexture(ApolloPlayer viewer, int locationX, boolean hardCore);

    /**
     * Resets an overwrote heart texture for the {@link ApolloPlayer}.
     *
     * @param viewer the player who is receiving the packet
     * @since 1.0.0
     */
    public abstract void resetHeartTexture(ApolloPlayer viewer);

}
