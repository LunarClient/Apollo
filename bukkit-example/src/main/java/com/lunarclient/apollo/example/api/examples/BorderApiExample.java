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
package com.lunarclient.apollo.example.api.examples;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.common.cuboid.Cuboid2D;
import com.lunarclient.apollo.example.common.modules.impl.BorderExample;
import com.lunarclient.apollo.module.border.Border;
import com.lunarclient.apollo.module.border.BorderModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.awt.Color;
import java.util.Optional;
import org.bukkit.entity.Player;

public class BorderApiExample extends BorderExample {

    private final BorderModule borderModule = Apollo.getModuleManager().getModule(BorderModule.class);

    @Override
    public void displayBorderExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresent(apolloPlayer -> {
            this.borderModule.displayBorder(apolloPlayer, Border.builder()
                .id("pvp-tagged-spawn")
                .world("world")
                .cancelEntry(true)
                .cancelExit(true)
                .canShrinkOrExpand(false)
                .color(Color.BLACK)
                .bounds(Cuboid2D.builder()
                    .minX(-50)
                    .minZ(-50)
                    .maxX(50)
                    .maxZ(50)
                    .build()
                )
                .durationTicks(1000)
                .build()
            );
        });
    }

    @Override
    public void removeBorderExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(apolloPlayer -> this.borderModule.removeBorder(apolloPlayer, "pvp-tagged-spawn"));
    }

    @Override
    public void resetBordersExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(this.borderModule::resetBorders);
    }

}
