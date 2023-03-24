package com.moonsworth.apollo.module.type;

import com.moonsworth.apollo.module.ApolloModule;
import com.moonsworth.apollo.option.ListOption;
import com.moonsworth.apollo.option.Option;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.player.ui.HeartTexture;
import io.leangen.geantyref.TypeToken;
import org.jetbrains.annotations.ApiStatus;

import java.util.Collections;
import java.util.List;

/**
 * Represents the heart texture module.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
public abstract class HeartTextures extends ApolloModule {

    /**
     * A list of heart textures.
     */
    public static final ListOption<HeartTexture> HEART_TEXTURES = Option.<HeartTexture>list()
        .node("textures").type(new TypeToken<List<HeartTexture>>() {})
        .defaultValue(Collections.emptyList()).notifyClient()
        .build();

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
    public abstract void sendHeartTexture(final ApolloPlayer player, final HeartTextures heartTexture);

    /**
     * Removes the {@link HeartTextures} from the {@link ApolloPlayer}.
     *
     * @param player the player
     * @since 1.0.0
     */
    public abstract void removeHeartTexture(final ApolloPlayer player);
}
