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
package com.lunarclient.apollo.example.api.mods;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.event.ApolloListener;
import com.lunarclient.apollo.event.EventBus;
import com.lunarclient.apollo.event.Listen;
import com.lunarclient.apollo.event.modsetting.ApolloUpdateModOptionEvent;
import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.mods.impl.ModFov;
import com.lunarclient.apollo.mods.impl.ModMinimap;
import com.lunarclient.apollo.mods.impl.ModWaypoints;
import com.lunarclient.apollo.module.modsetting.ModSettingModule;
import com.lunarclient.apollo.option.Options;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.util.Objects;
import java.util.Optional;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ApolloModStatusExample implements ApolloListener, Listener {

    private final ModSettingModule modSettingModule = Apollo.getModuleManager().getModule(ModSettingModule.class);

    public ApolloModStatusExample(ApolloExamplePlugin plugin) {
        EventBus.getBus().register(this);
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @Listen
    private void onApolloUpdateModOption(ApolloUpdateModOptionEvent event) {
        event.getPlayer().sendMessage(Component.text(event.getOption().getKey())
            .append(Component.text(" was updated to "))
            .append(Component.text(Objects.toString(event.getValue()))));
    }

    @EventHandler
    private void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();

        if (event.getBlock().getType() != Material.DIAMOND_BLOCK) {
            return;
        }

        this.printOptionStatusExample(player);
    }

    private void printOptionStatusExample(Player player) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(player.getUniqueId());
        apolloPlayerOpt.ifPresent(apolloPlayer -> {
            // https://lunarclient.dev/apollo/developers/mods/waypoints#available-options
            boolean waypointsEnabled = this.modSettingModule.getStatus(apolloPlayer, ModWaypoints.ENABLED);

            // https://lunarclient.dev/apollo/developers/mods/minimap#available-options
            float minimapScale = this.modSettingModule.getStatus(apolloPlayer, ModMinimap.SCALE);

            // https://lunarclient.dev/apollo/developers/mods/fov#available-options
            int fovDefaultFov = this.modSettingModule.getStatus(apolloPlayer, ModFov.DEFAULT_FOV);

            apolloPlayer.sendMessage(Component.text("Waypoints Enabled: ")
                .append(Component.text(waypointsEnabled)));

            apolloPlayer.sendMessage(Component.text("Minimap Scale: ")
                .append(Component.text(minimapScale)));

            apolloPlayer.sendMessage(Component.text("Fov Default Fov: ")
                .append(Component.text(fovDefaultFov)));
        });
    }

    @EventHandler
    private void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        Apollo.getPlayerManager().getPlayer(player.getUniqueId()).ifPresent(apolloPlayer -> {
            Options options = this.modSettingModule.getOptions();

            switch (message.toLowerCase()) {
                case "1": {
                    options.set(ModWaypoints.ENABLED, false);
                    options.set(ModWaypoints.DEATH_WAYPOINT, false);
                    player.sendMessage("=1");
                    break;
                }

                case "2": {
                    options.set(ModWaypoints.ENABLED, null);
                    options.set(ModWaypoints.DEATH_WAYPOINT, null);
                    player.sendMessage("=2");
                    break;
                }

                case "3": {
                    options.set(apolloPlayer, ModWaypoints.ENABLED, false);
                    options.set(apolloPlayer, ModWaypoints.DEATH_WAYPOINT, false);
                    player.sendMessage("=3");
                    break;
                }

                case "4": {
                    options.set(apolloPlayer, ModWaypoints.ENABLED, null);
                    options.set(apolloPlayer, ModWaypoints.DEATH_WAYPOINT, null);
                    player.sendMessage("=4");
                    break;
                }
            }
        });
    }

}
