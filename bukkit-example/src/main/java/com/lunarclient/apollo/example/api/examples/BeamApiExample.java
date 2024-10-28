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
import com.lunarclient.apollo.common.location.ApolloBlockLocation;
import com.lunarclient.apollo.example.common.modules.impl.BeamExample;
import com.lunarclient.apollo.module.beam.Beam;
import com.lunarclient.apollo.module.beam.BeamModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.awt.Color;
import java.util.Optional;
import org.bukkit.entity.Player;

public class BeamApiExample extends BeamExample {

    private final BeamModule beamModule = Apollo.getModuleManager().getModule(BeamModule.class);

    @Override
    public void displayBeamExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresent(apolloPlayer -> {
            this.beamModule.displayBeam(apolloPlayer, Beam.builder()
                .id("spawn-beacon")
                .color(Color.CYAN)
                .location(ApolloBlockLocation.builder()
                    .world("world")
                    .x(0)
                    .y(60)
                    .z(0)
                    .build()
                )
                .build()
            );
        });
    }

    @Override
    public void removeBeamExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(apolloPlayer -> this.beamModule.removeBeam(apolloPlayer, "spawn-beacon"));
    }

    @Override
    public void resetBeamsExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(this.beamModule::resetBeams);
    }

}
