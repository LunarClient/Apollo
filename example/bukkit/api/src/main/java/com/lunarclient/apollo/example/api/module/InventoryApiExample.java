/*
 * This file is part of Apollo, licensed under the MIT License.
 *
 * Copyright (c) 2026 Moonsworth
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
package com.lunarclient.apollo.example.api.module;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.common.button.ApolloButtonTooltip;
import com.lunarclient.apollo.common.button.content.ApolloButtonContent;
import com.lunarclient.apollo.event.ApolloListener;
import com.lunarclient.apollo.event.EventBus;
import com.lunarclient.apollo.event.Listen;
import com.lunarclient.apollo.event.modsetting.ApolloUpdateModOptionEvent;
import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.api.module.inventorybuttons.HubLayout;
import com.lunarclient.apollo.example.api.module.inventorybuttons.MenuLayout;
import com.lunarclient.apollo.example.api.module.inventorybuttons.MinigameLayout;
import com.lunarclient.apollo.example.api.module.inventorybuttons.StaffLayout;
import com.lunarclient.apollo.example.module.impl.InventoryExample;
import com.lunarclient.apollo.mods.impl.ModMinimap;
import com.lunarclient.apollo.mods.impl.ModWaypoints;
import com.lunarclient.apollo.module.inventory.InventoryModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class InventoryApiExample extends InventoryExample implements ApolloListener, Listener {

    private final InventoryModule inventoryModule = Apollo.getModuleManager().getModule(InventoryModule.class);

    private final Set<UUID> minigameViewers = new HashSet<>();

    public InventoryApiExample() {
        EventBus.getBus().register(this);
        Bukkit.getPluginManager().registerEvents(this, ApolloExamplePlugin.getInstance());
    }

    @Override
    public void displayMenuLayoutExample(Player viewer) {
        MenuLayout.display(this.inventoryModule, viewer);
    }

    @Override
    public void displayHubLayoutExample(Player viewer) {
        HubLayout.display(this.inventoryModule, viewer);
    }

    @Override
    public void displayMinigameLayoutExample(Player viewer) {
        this.minigameViewers.add(viewer.getUniqueId());
        MinigameLayout.display(this.inventoryModule, viewer);
    }

    @Override
    public void displayStaffLayoutExample(Player viewer) {
        StaffLayout.display(this.inventoryModule, viewer);
    }

    @Override
    public void removeInventoryButtonExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresent(apolloPlayer -> {
            this.inventoryModule.removeInventoryButton(apolloPlayer, "shop");
            this.inventoryModule.removeInventoryButton(apolloPlayer, "vote");
        });
    }

    @Override
    public void updateInventoryButtonExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresent(apolloPlayer -> this.inventoryModule.updateInventoryButton(apolloPlayer, "vote",
            ApolloButtonContent.builder()
                .append(Component.text("Thanks for voting!", NamedTextColor.GREEN))
                .scale(0.85F)
                .build(),
            ApolloButtonTooltip.of(Component.text("Come back tomorrow!", NamedTextColor.GRAY))));
    }

    @Override
    public void resetInventoryButtonsExample(Player viewer) {
        this.minigameViewers.remove(viewer.getUniqueId());

        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(this.inventoryModule::resetInventoryButtons);
    }

    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent event) {
        this.minigameViewers.remove(event.getPlayer().getUniqueId());
    }

    // Update Minigame layout if the player toggles their Minimap or Waypoint mod
    @Listen
    private void onApolloUpdateModOption(ApolloUpdateModOptionEvent event) {
        String optionKey = event.getOption().getKey();
        if (!ModMinimap.ENABLED.getKey().equals(optionKey) && !ModWaypoints.ENABLED.getKey().equals(optionKey)) {
            return;
        }

        UUID playerIdentifier = event.getPlayer().getUniqueId();
        if (!this.minigameViewers.contains(playerIdentifier)) {
            return;
        }

        Player viewer = Bukkit.getPlayer(playerIdentifier);
        if (viewer != null) {
            MinigameLayout.display(this.inventoryModule, viewer);
        }
    }

}
