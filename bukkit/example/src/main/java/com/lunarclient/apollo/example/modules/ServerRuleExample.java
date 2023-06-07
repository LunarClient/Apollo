package com.lunarclient.apollo.example.modules;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.module.serverrule.ServerRuleModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import org.bukkit.entity.Player;

import java.util.Optional;

public class ServerRuleExample {

    private final ServerRuleModule serverRuleModule = Apollo.getModuleManager().getModule(ServerRuleModule.class);

    public void setAntiPortalTraps(boolean value) {
        this.serverRuleModule.getOptions().set(ServerRuleModule.ANTI_PORTAL_TRAPS, value);
    }

    public void setOverrideNametagRenderDistance(Player viewer, boolean value) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresent(apolloPlayer -> {
            this.serverRuleModule.getOptions().set(apolloPlayer, ServerRuleModule.OVERRIDE_NAMETAG_RENDER_DISTANCE, value);
        });
    }

    public void setNametagRenderDistanceExample(int value) {
        this.serverRuleModule.getOptions().set(ServerRuleModule.NAMETAG_RENDER_DISTANCE, value);
    }
}
