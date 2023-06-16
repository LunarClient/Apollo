package com.lunarclient.apollo.example.modules;

import com.google.common.collect.Sets;
import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.module.modsetting.ModSettingModule;
import com.lunarclient.apollo.module.modsetting.ModSettings;
import com.lunarclient.apollo.module.modsetting.ModsSettings;
import com.lunarclient.apollo.player.ApolloPlayer;
import org.bukkit.entity.Player;

import java.util.Optional;

public class ModSettingsExample {

    private final ModSettingModule modSettingModule = Apollo.getModuleManager().getModule(ModSettingModule.class);

    private final ModsSettings settings = ModsSettings.builder()
        .settings(Sets.newHashSet(
            // Disables the SkyBlock Addons mod
            ModSettings.builder()
                .target("skyblockAddons") // Mod id's can be found at (@TRENTIN)
                .enable(false) // If the mod can be enabled
                .properties(null) // Properties can be found at (@TRENTIn)
                .build()
        ))
        .build();

    public void sendSettingsExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        // Sending the updated mod settings, "settings", built in the example above to the player.
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
