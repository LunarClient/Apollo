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
package com.lunarclient.apollo.example.debug.impl;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.common.cuboid.Cuboid2D;
import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.debug.Debug;
import com.lunarclient.apollo.example.debug.DebugTask;
import com.lunarclient.apollo.module.border.Border;
import com.lunarclient.apollo.module.border.BorderModule;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.NumberConversions;
import org.bukkit.util.Vector;

public class BorderCollisionTest extends Debug {

    private final BorderModule borderModule = Apollo.getModuleManager().getModule(BorderModule.class);
    private final Cuboid2D border = Cuboid2D.builder()
        .minX(200).minZ(200)
        .maxX(200).maxZ(200)
        .build();

    public BorderCollisionTest(Player player) {
        super(player);
    }

    @Override
    public Location startingLocation() {
        return new Location(DEBUG_WORLD, 200.5D, 100D, 200.5D);
    }

    @Override
    public double allowedOffset() {
        return 1.0D;
    }

    @Override
    public List<DebugTask> steps() {
        List<DebugTask> tasks = new LinkedList<>();

        tasks.add(new DebugTask(1, (player, apolloPlayer) -> {
            this.borderModule.displayBorder(apolloPlayer, this.createBorder()
                .id("border-debug")
                .bounds(this.border)
                .build());
        }, "Displaying 1x1 Border"));

        tasks.add(new DebugTask(60, (player, apolloPlayer) -> {
            new BounceTask(10, 3);
        }, "Boucning Player"));

        tasks.add(new DebugTask(10, (player, apolloPlayer) -> {
            this.borderModule.removeBorder(apolloPlayer, "border-debug");
        }, "Clear Border"));

        tasks.add(new DebugTask(10, (player, apolloPlayer) -> {
            player.teleport(this.startingLocation());
        }, "Teleporting to starting location"));

        tasks.add(new DebugTask(1, (player, apolloPlayer) -> {
            this.borderModule.displayBorder(apolloPlayer, this.createBorder()
                .id("border-debug-north")
                .bounds(Cuboid2D.builder()
                    .minX(200).maxX(200)
                    .minZ(199).maxZ(199)
                    .build())
                .build());

            this.borderModule.displayBorder(apolloPlayer, this.createBorder()
                .id("border-debug-east")
                .bounds(Cuboid2D.builder()
                    .minX(201).maxX(201)
                    .minZ(200).maxZ(200)
                    .build())
                .build());

            this.borderModule.displayBorder(apolloPlayer, this.createBorder()
                .id("border-debug-south")
                .bounds(Cuboid2D.builder()
                    .minX(200).maxX(200)
                    .minZ(201).maxZ(201)
                    .build())
                .build());

            this.borderModule.displayBorder(apolloPlayer, this.createBorder()
                .id("border-debug-west")
                .bounds(Cuboid2D.builder()
                    .minX(199).maxX(199)
                    .minZ(200).maxZ(200)
                    .build())
                .build());
        }, "Displaying 4 1x1 Borders"));

        tasks.add(new DebugTask(60, (player, apolloPlayer) -> {
            new BounceTask(10, 3);
        }, "Boucning Player"));

        tasks.add(new DebugTask(10, (player, apolloPlayer) -> {
            this.borderModule.removeBorder(apolloPlayer, "border-debug-north");
            this.borderModule.removeBorder(apolloPlayer, "border-debug-east");
            this.borderModule.removeBorder(apolloPlayer, "border-debug-south");
            this.borderModule.removeBorder(apolloPlayer, "border-debug-west");
        }, "Clear Borders"));

        return tasks;
    }

    private Border.BorderBuilder createBorder() {
        return Border.builder()
            .world(DEBUG_WORLD.getName())
            .cancelEntry(true)
            .cancelExit(true)
            .canShrinkOrExpand(false)
            .color(Color.RED)
            .durationTicks(1000);
    }

    private void bouncePlayer(Player player) {
        ThreadLocalRandom random = ThreadLocalRandom.current();

        double x = (random.nextDouble() - 0.5) * 2;
        double y = random.nextDouble() * 1.2 + 0.1;
        double z = (random.nextDouble() - 0.5) * 2;

        Vector velocity = new Vector(x, y, z);
        player.setVelocity(velocity);
    }

    @EventHandler(ignoreCancelled = true)
    private void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player != this.player || this.failed) {
            return;
        }

        Location location = player.getLocation();

        Location startingLocation = this.startingLocation();
        double distance = Math.sqrt(NumberConversions.square(location.getX() - startingLocation.getX())
            + NumberConversions.square(location.getZ() - startingLocation.getZ()));

        if (distance > this.allowedOffset()) {
            this.fail();
        }
    }

    private class BounceTask extends BukkitRunnable {

        private final int amount;

        private int bounces;

        BounceTask(int amount, int ticks) {
            this.amount = amount;

            this.runTaskTimer(
                ApolloExamplePlugin.getPlugin(),
                ticks, ticks
            );
        }

        @Override
        public void run() {
            BorderCollisionTest.this.bouncePlayer(player);

            if (++this.bounces == this.amount) {
                this.cancel();
            }
        }
    }
}
