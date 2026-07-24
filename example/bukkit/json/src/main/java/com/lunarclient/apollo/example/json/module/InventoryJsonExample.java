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
package com.lunarclient.apollo.example.json.module;

import com.google.gson.JsonObject;
import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.json.module.inventorybuttons.HubLayout;
import com.lunarclient.apollo.example.json.module.inventorybuttons.InventoryButtonParts;
import com.lunarclient.apollo.example.json.module.inventorybuttons.MenuLayout;
import com.lunarclient.apollo.example.json.module.inventorybuttons.MinigameLayout;
import com.lunarclient.apollo.example.json.module.inventorybuttons.StaffLayout;
import com.lunarclient.apollo.example.json.util.JsonPacketUtil;
import com.lunarclient.apollo.example.module.impl.InventoryExample;
import com.lunarclient.apollo.example.util.ServerUtil;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class InventoryJsonExample extends InventoryExample implements Listener {

    private final Set<UUID> staffViewers = ConcurrentHashMap.newKeySet();
    private final Set<UUID> menuViewers = ConcurrentHashMap.newKeySet();
    private final Set<UUID> minigameViewers = ConcurrentHashMap.newKeySet();

    private final Set<UUID> openInventories = ConcurrentHashMap.newKeySet();
    private volatile boolean inventoryTrackingSeen;

    public InventoryJsonExample() {
        if (ServerUtil.isFolia()) {
            this.runFoliaLiveButtonTask();
        } else {
            this.runBukkitLiveButtonTask();
        }

        Bukkit.getPluginManager().registerEvents(this, ApolloExamplePlugin.getInstance());
    }

    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent event) {
        UUID playerIdentifier = event.getPlayer().getUniqueId();

        this.staffViewers.remove(playerIdentifier);
        this.menuViewers.remove(playerIdentifier);
        this.minigameViewers.remove(playerIdentifier);
        this.openInventories.remove(playerIdentifier);
    }

    @Override
    public void displayMenuLayoutExample(Player viewer) {
        MenuLayout.display(viewer);
        this.menuViewers.add(viewer.getUniqueId());
    }

    @Override
    public void displayHubLayoutExample(Player viewer) {
        HubLayout.display(viewer);
    }

    @Override
    public void displayMinigameLayoutExample(Player viewer) {
        MinigameLayout.display(viewer);
        this.minigameViewers.add(viewer.getUniqueId());
    }

    @Override
    public void displayStaffLayoutExample(Player viewer) {
        StaffLayout.display(viewer);
        this.staffViewers.add(viewer.getUniqueId());
    }

    @Override
    public void removeInventoryButtonExample(Player viewer) {
        JsonObject shopMessage = new JsonObject();
        shopMessage.addProperty("@type", "type.googleapis.com/lunarclient.apollo.inventory.v1.RemoveInventoryButtonMessage");
        shopMessage.addProperty("id", "shop");

        JsonObject voteMessage = new JsonObject();
        voteMessage.addProperty("@type", "type.googleapis.com/lunarclient.apollo.inventory.v1.RemoveInventoryButtonMessage");
        voteMessage.addProperty("id", "vote");

        JsonPacketUtil.sendPacket(viewer, shopMessage);
        JsonPacketUtil.sendPacket(viewer, voteMessage);
    }

    @Override
    public void updateInventoryButtonExample(Player viewer) {
        JsonObject update = new JsonObject();
        update.add("content", InventoryButtonParts.createContentObject(0.85F,
            InventoryButtonParts.textPart(Component.text("Thanks for voting!", NamedTextColor.GREEN))));
        update.add("tooltip", InventoryButtonParts.createTooltipObject(
            Component.text("Come back tomorrow!", NamedTextColor.GRAY)));

        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.inventory.v1.UpdateInventoryButtonMessage");
        message.addProperty("id", "vote");
        message.add("update", update);

        JsonPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void resetInventoryButtonsExample(Player viewer) {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.inventory.v1.ResetInventoryButtonsMessage");

        JsonPacketUtil.sendPacket(viewer, message);

        UUID playerIdentifier = viewer.getUniqueId();
        this.staffViewers.remove(playerIdentifier);
        this.menuViewers.remove(playerIdentifier);
        this.minigameViewers.remove(playerIdentifier);
    }

    public void handleInventoryOpen(Player player) {
        this.inventoryTrackingSeen = true;
        this.openInventories.add(player.getUniqueId());
        this.sendLiveUpdates(player);
    }

    public void handleInventoryClose(Player player) {
        this.inventoryTrackingSeen = true;
        this.openInventories.remove(player.getUniqueId());
    }

    private void runBukkitLiveButtonTask() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(ApolloExamplePlugin.getInstance(),
            this::broadcastLiveButtonUpdates, 50L, 50L);
    }

    private void runFoliaLiveButtonTask() {
        Bukkit.getAsyncScheduler().runAtFixedRate(ApolloExamplePlugin.getInstance(),
            task -> this.broadcastLiveButtonUpdates(), 2500L, 2500L, TimeUnit.MILLISECONDS);
    }

    private void broadcastLiveButtonUpdates() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            UUID playerIdentifier = player.getUniqueId();

            if (this.inventoryTrackingSeen && !this.openInventories.contains(playerIdentifier)) {
                continue;
            }

            this.sendLiveUpdates(player);
        }
    }

    private void sendLiveUpdates(Player player) {
        UUID playerIdentifier = player.getUniqueId();

        if (this.staffViewers.contains(playerIdentifier)) {
            StaffLayout.sendUpdates(player);
        }

        if (this.menuViewers.contains(playerIdentifier)) {
            MenuLayout.sendBalanceUpdate(player);
        }

        if (this.minigameViewers.contains(playerIdentifier)) {
            MinigameLayout.sendKillsUpdate(player);
        }
    }

}
