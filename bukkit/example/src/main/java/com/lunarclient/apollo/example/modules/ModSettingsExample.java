/*
 * This file is part of Apollo, licensed under the MIT License.
 *
 * Copyright (c) 2023 Moonsworth
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.lunarclient.apollo.example.modules;

import com.google.common.collect.Sets;
import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.module.modsetting.ModSettingModule;
import com.lunarclient.apollo.module.modsetting.ModSettings;
import com.lunarclient.apollo.module.modsetting.ModsSettings;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.util.Optional;
import org.bukkit.entity.Player;

public class ModSettingsExample {

    private final ModSettingModule modSettingModule = Apollo.getModuleManager().getModule(ModSettingModule.class);

    private final ModsSettings settings = ModsSettings.builder()
        .settings(Sets.newHashSet(
            // Disables the SkyBlock Addons mod
            ModSettings.builder()
                .target("skyblockAddons") // The Mod ID you want to change
                .enable(false) // If the mod can be enabled
                .properties(null)
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
