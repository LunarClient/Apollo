package com.lunarclient.apollo.example.modules;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.module.hearttexture.HeartTextureModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import org.bukkit.entity.Player;

import java.util.Optional;

public class HeartTextureExample {

    private final HeartTextureModule heartTextureModule = Apollo.getModuleManager().getModule(HeartTextureModule.class);

    public void overrideHeartTextureExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresent(apolloPlayer -> {
            this.heartTextureModule.overrideHeartTexture(apolloPlayer,
                5, false);
        });
    }

    public void resetHeartTextureExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        // Removes the heart texture from the player, using heart texture type.
        apolloPlayerOpt.ifPresent(this.heartTextureModule::resetHeartTexture);
    }

}
