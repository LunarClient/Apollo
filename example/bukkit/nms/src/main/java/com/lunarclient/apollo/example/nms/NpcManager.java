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
package com.lunarclient.apollo.example.nms;

import com.mojang.authlib.GameProfile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoRemovePacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket;
import net.minecraft.network.protocol.game.ClientboundRemoveEntitiesPacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ClientInformation;
import net.minecraft.server.level.ParticleStatus;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.ChatVisiblity;
import net.minecraft.world.level.GameType;
import net.minecraft.world.phys.Vec3;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;

public final class NpcManager implements Listener {

    private static final ClientInformation NPC_CLIENT_INFO = new ClientInformation(
        "en_us", 2, ChatVisiblity.HIDDEN, false, 0x7F,
        HumanoidArm.RIGHT, false, false, ParticleStatus.ALL
    );

    private final Map<UUID, PlayerNpc> npcs = new HashMap<>();
    private final JavaPlugin plugin;

    public NpcManager(JavaPlugin plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
        Bukkit.getScheduler().runTask(plugin, this::spawnDefaultNpcs);
    }

    public void removeNpc(UUID uuid) {
        PlayerNpc npc = this.npcs.remove(uuid);
        if (npc == null) {
            return;
        }

        this.despawnNpcs(npc);
    }

    public void removeAll() {
        for (PlayerNpc npc : new ArrayList<>(this.npcs.values())) {
            this.despawnNpcs(npc);
        }

        this.npcs.clear();
    }

    public Optional<PlayerNpc> findByName(String name) {
        return this.npcs.values().stream()
            .filter(npc -> npc.getName().equalsIgnoreCase(name))
            .findFirst();
    }

    public Collection<PlayerNpc> getNpcs() {
        return new ArrayList<>(this.npcs.values());
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        ServerPlayer viewer = ((CraftPlayer) event.getPlayer()).getHandle();
        for (PlayerNpc npc : this.npcs.values()) {
            this.showNpc(viewer, npc);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        ServerPlayer viewer = ((CraftPlayer) event.getPlayer()).getHandle();
        for (PlayerNpc npc : this.npcs.values()) {
            this.hideNpc(viewer, npc);
        }
    }

    private void spawnDefaultNpcs() {
        this.spawnNpc("Apollo", new Location(Bukkit.getWorld("world"), 20.5, 65, 5.5, 90f, 0f));
    }

    public @Nullable PlayerNpc spawnNpc(String name, Location location) {
        World world = location.getWorld();
        if (world == null) {
            return null;
        }

        MinecraftServer server = MinecraftServer.getServer();
        ServerLevel level = ((CraftWorld) world).getHandle();

        UUID npcUuid = new UUID(UUID.randomUUID().getMostSignificantBits(), 0L);
        GameProfile profile = new GameProfile(npcUuid, name);
        ServerPlayer npc = new ServerPlayer(server, level, profile, NpcManager.NPC_CLIENT_INFO);

        npc.setPos(location.getX(), location.getY(), location.getZ());
        npc.setYRot(location.getYaw());
        npc.setXRot(location.getPitch());

        PlayerNpc playerNpc = new PlayerNpc(npc.getUUID(), name, location.clone(), npc);
        this.npcs.put(playerNpc.getUuid(), playerNpc);

        for (ServerPlayer viewer : server.getPlayerList().getPlayers()) {
            this.showNpc(viewer, playerNpc);
        }

        return playerNpc;
    }

    private void showNpc(ServerPlayer viewer, PlayerNpc npc) {
        ServerPlayer entity = npc.getHandle();
        Location location = npc.getLocation();
        GameProfile profile = entity.getGameProfile();

        ClientboundPlayerInfoUpdatePacket.Entry entry = new ClientboundPlayerInfoUpdatePacket.Entry(
            entity.getUUID(), profile, true, 0, GameType.CREATIVE, null, true, 0, null);

        viewer.connection.send(new ClientboundPlayerInfoUpdatePacket(
            EnumSet.of(ClientboundPlayerInfoUpdatePacket.Action.ADD_PLAYER), entry));
        viewer.connection.send(new ClientboundAddEntityPacket(
            entity.getId(), entity.getUUID(),
            location.getX(), location.getY(), location.getZ(),
            location.getPitch(), location.getYaw(),
            entity.getType(), 0, Vec3.ZERO, location.getYaw()));
        viewer.connection.send(new ClientboundSetEntityDataPacket(
            entity.getId(), entity.getEntityData().getNonDefaultValues()));

        Bukkit.getScheduler().runTaskLater(this.plugin, () -> {
            viewer.connection.send(new ClientboundPlayerInfoRemovePacket(List.of(entity.getUUID())));
        }, 5L);
    }

    private void hideNpc(ServerPlayer viewer, PlayerNpc npc) {
        viewer.connection.send(new ClientboundRemoveEntitiesPacket(npc.getEntityId()));
        viewer.connection.send(new ClientboundPlayerInfoRemovePacket(List.of(npc.getUuid())));
    }

    private void despawnNpcs(PlayerNpc npc) {
        for (ServerPlayer player : MinecraftServer.getServer().getPlayerList().getPlayers()) {
            this.hideNpc(player, npc);
        }
    }

}
