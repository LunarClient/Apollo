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
package com.lunarclient.apollo.example.api.debug;

import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.api.debug.payload.PayloadListener;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class DebugManager implements Listener {

    @Getter
    private static DebugManager instance;

    private final Map<UUID, Debug> players;

    public DebugManager() {
        instance = this;

        this.players = new HashMap<>();

        new PayloadListener();

        Bukkit.getPluginManager().registerEvents(this, ApolloExamplePlugin.getInstance());
    }

    public void remove(Player player) {
        this.players.remove(player.getUniqueId());
    }

    public void start(Player player, Debug debug) {
        if (this.players.containsKey(player.getUniqueId())) {
            player.sendMessage("Already debugging!");
            return;
        }

        this.players.put(player.getUniqueId(), debug);
        debug.start();

        player.sendMessage("Starting debug!");
    }

    public void stop(Player player) {
        Debug debug = this.players.remove(player.getUniqueId());

        if (debug == null) {
            player.sendMessage("Not debugging!");
            return;
        }

        debug.end(true);
    }

}
