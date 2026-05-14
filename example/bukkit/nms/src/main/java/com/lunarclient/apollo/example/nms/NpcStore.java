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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public final class NpcStore {

    private static final String FILE_NAME = "npcs.json";
    private static final Type ENTRY_LIST_TYPE = new TypeToken<List<Entry>>() { }.getType();

    private final JavaPlugin plugin;
    private final Gson gson;
    private final File file;

    public NpcStore(JavaPlugin plugin) {
        this.plugin = plugin;

        this.gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(CommandCosmetic.Options.class, new CosmeticOptionsAdapter())
            .create();

        this.file = new File(plugin.getDataFolder(), NpcStore.FILE_NAME);

        File dataFolder = plugin.getDataFolder();
        if (!dataFolder.exists() && !dataFolder.mkdirs()) {
            plugin.getLogger().warning("Failed to create plugin data folder at " + dataFolder.getAbsolutePath());
        }
    }

    public boolean exists() {
        return this.file.isFile();
    }

    public List<Entry> load() {
        if (!this.file.isFile()) {
            return new ArrayList<>();
        }

        try (Reader reader = Files.newBufferedReader(this.file.toPath(), StandardCharsets.UTF_8)) {
            List<Entry> entries = this.gson.fromJson(reader, NpcStore.ENTRY_LIST_TYPE);
            return entries != null ? entries : new ArrayList<>();
        } catch (IOException e) {
            this.plugin.getLogger().warning("Failed to read " + NpcStore.FILE_NAME + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void save(Collection<PlayerNpc> npcs) {
        List<Entry> entries = new ArrayList<>(npcs.size());
        for (PlayerNpc npc : npcs) {
            Location location = npc.getLocation();
            World world = location.getWorld();
            if (world == null) {
                continue;
            }

            entries.add(new Entry(
                npc.getUuid().toString(), npc.getName(), world.getName(),
                location.getX(), location.getY(), location.getZ(),
                location.getYaw(), location.getPitch(),
                new ArrayList<>(npc.getCosmetics())
            ));
        }

        try {
            Files.createDirectories(this.file.toPath().getParent());
        } catch (IOException e) {
            this.plugin.getLogger().warning("Failed to create plugin data folder for " + NpcStore.FILE_NAME + ": " + e.getMessage());
            return;
        }

        try (Writer writer = Files.newBufferedWriter(this.file.toPath(), StandardCharsets.UTF_8)) {
            this.gson.toJson(entries, NpcStore.ENTRY_LIST_TYPE, writer);
        } catch (IOException e) {
            this.plugin.getLogger().warning("Failed to write " + NpcStore.FILE_NAME + ": " + e.getMessage());
        }
    }

    public Location toLocation(Entry entry) {
        World world = Bukkit.getWorld(entry.world);

        if (world == null) {
            return null;
        }

        return new Location(world, entry.x, entry.y, entry.z, entry.yaw, entry.pitch);
    }

    @Getter
    @RequiredArgsConstructor
    public static final class Entry {

        private final String uuid;
        private final String name;
        private final String world;
        private final double x;
        private final double y;
        private final double z;
        private final float yaw;
        private final float pitch;
        private final List<CommandCosmetic> cosmetics;

        public UUID getUuid() {
            return UUID.fromString(this.uuid);
        }
    }

}
