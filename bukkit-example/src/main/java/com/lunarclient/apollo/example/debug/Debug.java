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
package com.lunarclient.apollo.example.debug;

import com.lunarclient.apollo.example.ApolloExamplePlugin;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

public abstract class Debug implements Listener {

    public static final World DEBUG_WORLD = Bukkit.getWorld("world");

    protected final Player player;

    private List<DebugTask> steps;
    private int phase;
    protected boolean failed;

    public Debug(Player player) {
        this.player = player;
        this.steps = this.steps();
        this.phase = 1;

        Bukkit.getPluginManager().registerEvents(this, ApolloExamplePlugin.getPlugin());
    }

    public abstract List<DebugTask> steps();

    public abstract Location startingLocation();

    public abstract double allowedOffset();

    public void start() {
        this.player.teleport(this.startingLocation());

        Bukkit.getScheduler().runTaskLater(
            ApolloExamplePlugin.getPlugin(),
            () -> this.nextStepOrEnd(), 20L
        );
    }

    public void nextStepOrEnd() {
        if (this.steps.isEmpty()) {
            this.end(false);
            return;
        }

        DebugTask activeTask = this.steps.remove(0);
        if (activeTask == null) {
            this.end(false);
            return;
        }

        this.player.sendMessage("Phase " + ++this.phase);
        activeTask.run(this.player);

        Bukkit.getScheduler().runTaskLater(
            ApolloExamplePlugin.getPlugin(),
            () -> this.nextStepOrEnd(), activeTask.getDurationTicks()
        );
    }

    public void fail() {
        this.failed = true;
        this.player.sendMessage(ChatColor.RED + "Failed testing at phase " + this.phase);
    }

    public void end(boolean forced) {
        if (forced) {
            this.player.sendMessage(ChatColor.DARK_RED + "Stopped testing...");
        } else if (this.failed) {
            this.player.sendMessage(ChatColor.RED + "Failed testing...");
        } else {
            this.player.sendMessage(ChatColor.GREEN + "Passed testing...");
        }

        HandlerList.unregisterAll(this);
        DebugManager.getInstance().remove(this.player);
    }

}
