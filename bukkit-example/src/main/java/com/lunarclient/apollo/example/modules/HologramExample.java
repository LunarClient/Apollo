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
package com.lunarclient.apollo.example.modules;

import com.google.common.collect.Lists;
import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.common.location.ApolloLocation;
import com.lunarclient.apollo.module.hologram.Hologram;
import com.lunarclient.apollo.module.hologram.HologramModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.recipients.Recipients;
import java.util.Optional;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;

public class HologramExample {

    private final HologramModule hologramModule = Apollo.getModuleManager().getModule(HologramModule.class);

    public void displayHologramExample() {
        this.hologramModule.displayHologram(Recipients.ofEveryone(), Hologram.builder()
            .id("welcome-hologram")
            .location(ApolloLocation.builder()
                .world("world")
                .x(5)
                .y(105)
                .z(0)
                .build())
            .lines(Lists.newArrayList(
                Component.text()
                    .content("Welcome to my server!")
                    .color(NamedTextColor.RED)
                    .decorate(TextDecoration.BOLD, TextDecoration.UNDERLINED)
                    .build(),
                Component.text()
                    .content("Type /help to get started!")
                    .build()
            ))
            .showThroughWalls(true)
            .showShadow(false)
            .showBackground(true)
            .build()
        );
    }

    public void removeHologramExample() {
        this.hologramModule.removeHologram(Recipients.ofEveryone(), "welcome-hologram");
    }

    public void resetHologramsExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(this.hologramModule::resetHolograms);
    }

}
