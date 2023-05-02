package com.lunarclient.apollo.example.modules;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.module.staffmod.StaffMod;
import com.lunarclient.apollo.module.staffmod.StaffModModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Optional;

public class StaffModExample {

    private final StaffModModule staffModModule = Apollo.getModuleManager().getModule(StaffModModule.class);

    public void enableStaffModsExample(Player viewer) {
        if (!viewer.hasPermission("apollo.staff")) return;

        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(apolloPlayer -> this.staffModModule.enableStaffMods(apolloPlayer, List.of(StaffMod.XRAY)));
    }

    public void disableStaffModsExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(apolloPlayer -> this.staffModModule.disableStaffMods(apolloPlayer, List.of(StaffMod.XRAY)));
    }

}
