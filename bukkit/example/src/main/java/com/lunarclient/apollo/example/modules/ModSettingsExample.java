package com.lunarclient.apollo.example.modules;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.module.modsetting.ModSettingModule;
import com.lunarclient.apollo.module.modsetting.ModSettings;
import com.lunarclient.apollo.module.modsetting.ModsSettings;
import com.lunarclient.apollo.player.ApolloPlayer;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.Set;

public class ModSettingsExample {

    private final ModSettingModule modSettingModule = Apollo.getModuleManager().getModule(ModSettingModule.class);

    private final ModsSettings settings = ModsSettings.builder()
        .settings(Set.of(
            // Disables the SkyBlock Addons mod
            ModSettings.builder()
                .target("skyblockAddons") // Mod id's can be found at (@TRENTIN)
                .enable(false)
                .properties(null) // Properties can be found at (@TRENTIn)
                .build()
        ))
        .build();

    public void sendSettingsExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(apolloPlayer -> this.modSettingModule.sendSettings(apolloPlayer, this.settings));
    }

    public void resetSettingsExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(this.modSettingModule::resetSettings);
    }

    public void broadcastSettingsExample() {
        this.modSettingModule.broadcastSettings(this.settings);
    }

}
