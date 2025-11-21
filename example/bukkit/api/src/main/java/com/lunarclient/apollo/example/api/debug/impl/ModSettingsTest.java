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
package com.lunarclient.apollo.example.api.debug.impl;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.event.ApolloListener;
import com.lunarclient.apollo.event.EventBus;
import com.lunarclient.apollo.event.Listen;
import com.lunarclient.apollo.event.modsetting.ApolloUpdateModOptionEvent;
import com.lunarclient.apollo.event.player.ApolloPlayerHandshakeEvent;
import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.mods.impl.ModWaypoints;
import com.lunarclient.apollo.module.modsetting.ModSettingModule;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Predicate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitTask;

public class ModSettingsTest implements Listener, ApolloListener {

    private static final Component PREFIX = Component.text("[Apollo] [ModSettingsTest] ", NamedTextColor.YELLOW);
    private static final Component FAILURE = Component.text(" ❌ ", NamedTextColor.RED);
    private static final Component SUCCESS = Component.text(" ✔ ", NamedTextColor.GREEN);

    private final ModSettingModule modSettingModule = Apollo.getModuleManager().getModule(ModSettingModule.class);
    private final Map<UUID, List<OptionChange>> changes = new HashMap<>();
    private final Map<UUID, BukkitTask> tasks = new HashMap<>();

    public ModSettingsTest() {
        EventBus.getBus().register(this);
        Bukkit.getPluginManager().registerEvents(this, ApolloExamplePlugin.getInstance());
    }

    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        BukkitTask task = this.tasks.remove(uuid);

        if (task != null) {
            task.cancel();
        }

        this.changes.remove(uuid);
    }

    @Listen
    private void onApolloPlayerHandshake(ApolloPlayerHandshakeEvent event) {
        ApolloPlayer player = event.getPlayer();
        BukkitTask task = Bukkit.getScheduler().runTaskLater(ApolloExamplePlugin.getInstance(), () -> {
            this.changeOptions(player);
            this.checkOptions(player);
        }, 20 * 3);

        this.tasks.put(player.getUniqueId(), task);
    }

    private void checkOptions(ApolloPlayer player) {
        BukkitTask task = Bukkit.getScheduler().runTaskLater(ApolloExamplePlugin.getInstance(), () -> {
            List<OptionChange> changes = this.changes.getOrDefault(player.getUniqueId(), new ArrayList<>());
            if (!changes.isEmpty()) {
                Component message = PREFIX
                    .append(Component.text("Failed to receive "))
                    .append(Component.text(changes.size(), NamedTextColor.RED))
                    .append(Component.text(" option changes!", NamedTextColor.YELLOW))
                    .append(FAILURE);

                player.sendMessage(message);
                this.changes.remove(player.getUniqueId());
            }
        }, 20 * 10);

        this.tasks.put(player.getUniqueId(), task);
    }

    @Listen
    private void onApolloUpdateModOption(ApolloUpdateModOptionEvent event) {
        ApolloPlayer player = event.getPlayer();
        Option<?, ?, ?> option = event.getOption();
        Object value = event.getValue();

        List<OptionChange> changes = this.changes.getOrDefault(player.getUniqueId(), new ArrayList<>());
        boolean removed = false;

        Predicate<OptionChange> isEventOption = change -> change.getKey().equals(option.getKey())
            && (change.getValue() == null || Objects.equals(change.getValue(), value));

        Iterator<OptionChange> iterator = changes.iterator();
        while (iterator.hasNext()) {
            OptionChange change = iterator.next();
            if (isEventOption.test(change)) {
                Object statusApiValue = this.modSettingModule.getStatus(player, option);

                if (!Objects.equals(statusApiValue, value)) {
                    Component message = PREFIX
                        .append(Component.text("Status API returned wrong value \""))
                        .append(Component.text(statusApiValue.toString()))
                        .append(Component.text("\", expected \""))
                        .append(Component.text(value == null ? "null" : value.toString()))
                        .append(Component.text("\" for option \""))
                        .append(Component.text(option.getKey()))
                        .append(Component.text("\"!"))
                        .append(FAILURE);

                    player.sendMessage(message);
                }

                iterator.remove();
                removed = true;
            }
        }

        if (removed && changes.isEmpty()) {
            Component message = PREFIX
                .append(Component.text("Mod Setting Module tests passed!"))
                .append(SUCCESS);

            player.sendMessage(message);
        }

    }

    private void changeOptions(ApolloPlayer player) {
        this.changeOption(player, ModWaypoints.ENABLED, false);
        this.changeOption(player, ModWaypoints.DEATH_WAYPOINT, false);

        this.changeOption(player, ModWaypoints.ENABLED, null);
        this.changeOption(player, ModWaypoints.DEATH_WAYPOINT, null);
    }

    private void changeOption(ApolloPlayer player, Option<?, ?, ?> option, Object value) {
        this.modSettingModule.getOptions().set(option, value);

        this.changes.computeIfAbsent(player.getUniqueId(), k -> new ArrayList<>())
            .add(new OptionChange(option.getKey(), value));
    }

    @Getter
    @ToString
    @RequiredArgsConstructor
    private static class OptionChange {

        private final String key;
        private final Object value;

    }

}
