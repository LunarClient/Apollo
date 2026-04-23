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
import net.minecraft.network.protocol.Packet;
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
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;

public final class NpcManager {

    private static final int ALL_SKIN_LAYERS = 0x7F;

    private static final ClientInformation NPC_CLIENT_INFO = new ClientInformation(
        "en_us", 2, ChatVisiblity.HIDDEN, false, ALL_SKIN_LAYERS,
        HumanoidArm.RIGHT, false, false, ParticleStatus.ALL
    );

    private final Map<UUID, PlayerNpc> npcs = new HashMap<>();
    private final JavaPlugin plugin;

    public NpcManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public @Nullable PlayerNpc spawnNpc(String name, Location location) {
        World world = location.getWorld();
        if (world == null) {
            return null;
        }

        MinecraftServer server = MinecraftServer.getServer();
        ServerLevel level = ((CraftWorld) world).getHandle();

        GameProfile profile = new GameProfile(UUID.randomUUID(), name);
        ServerPlayer npc = new ServerPlayer(server, level, profile, NPC_CLIENT_INFO);

        npc.setPos(location.getX(), location.getY(), location.getZ());
        npc.setYRot(location.getYaw());
        npc.setXRot(location.getPitch());

        ClientboundPlayerInfoUpdatePacket.Entry entry = new ClientboundPlayerInfoUpdatePacket.Entry(
            npc.getUUID(), profile, true, 0, GameType.CREATIVE, null, true, 0, null);

        this.broadcastPacket(new ClientboundPlayerInfoUpdatePacket(
            EnumSet.of(ClientboundPlayerInfoUpdatePacket.Action.ADD_PLAYER), entry));

        this.broadcastPacket(new ClientboundAddEntityPacket(
            npc.getId(), npc.getUUID(),
            location.getX(), location.getY(), location.getZ(),
            location.getPitch(), location.getYaw(),
            npc.getType(), 0, Vec3.ZERO, location.getYaw()));

        this.broadcastPacket(new ClientboundSetEntityDataPacket(
            npc.getId(), npc.getEntityData().getNonDefaultValues()));

        Bukkit.getScheduler().runTaskLater(this.plugin, () -> {
            this.broadcastPacket(new ClientboundPlayerInfoRemovePacket(List.of(npc.getUUID())));
        }, 5L);

        PlayerNpc playerNpc = new PlayerNpc(npc.getUUID(), name, location.clone(), npc);
        this.npcs.put(playerNpc.getUuid(), playerNpc);

        return playerNpc;
    }

    public void removeNpc(UUID uuid) {
        PlayerNpc npc = this.npcs.remove(uuid);
        if (npc == null) {
            return;
        }

        this.despawnNpc(npc);
    }

    public void removeAll() {
        for (PlayerNpc npc : new ArrayList<>(this.npcs.values())) {
            this.despawnNpc(npc);
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

    private void despawnNpc(PlayerNpc npc) {
        this.broadcastPacket(new ClientboundRemoveEntitiesPacket(npc.getEntityId()));
        this.broadcastPacket(new ClientboundPlayerInfoRemovePacket(List.of(npc.getUuid())));
    }

    private void broadcastPacket(Packet<?> packet) {
        for (ServerPlayer player : MinecraftServer.getServer().getPlayerList().getPlayers()) {
            player.connection.send(packet);
        }
    }

}
