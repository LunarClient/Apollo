package com.lunarclient.apollo.example.modules;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.module.hearttexture.HeartTexture;
import com.lunarclient.apollo.module.hearttexture.HeartTextureModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.Optional;

public class HeartTextureExample {

    private final HeartTextureModule heartTextureModule = Apollo.getModuleManager().getModule(HeartTextureModule.class);

    public void overrideHeartTextureExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresentOrElse(apolloPlayer -> {
            this.heartTextureModule.overrideHeartTexture(apolloPlayer,
                HeartTexture.NORMAL,
                32
            );
        }, () -> viewer.sendMessage(Component.text("Join with Lunar Client to test this feature!")));
    }

    public void resetHeartTextureExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        // Removes the heart texture from the player, using heart texture type.
        apolloPlayerOpt.ifPresent(apolloPlayer -> this.heartTextureModule.resetHeartTexture(apolloPlayer, HeartTexture.NORMAL));
    }

}
