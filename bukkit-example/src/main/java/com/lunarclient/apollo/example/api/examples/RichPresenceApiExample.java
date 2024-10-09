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
import com.lunarclient.apollo.example.common.modules.impl.RichPresenceExample;
import com.lunarclient.apollo.module.richpresence.RichPresenceModule;
import com.lunarclient.apollo.module.richpresence.ServerRichPresence;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.util.Optional;
import org.bukkit.entity.Player;

public class RichPresenceApiExample extends RichPresenceExample {

    private final RichPresenceModule richPresenceModule = Apollo.getModuleManager().getModule(RichPresenceModule.class);

    @Override
    public void overrideServerRichPresenceExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresent(apolloPlayer -> {
            this.richPresenceModule.overrideServerRichPresence(apolloPlayer, ServerRichPresence.builder()
                .gameName("BedWars")
                .gameVariantName("Solo")
                .gameState("In Game")
                .playerState("Playing")
                .mapName("Winter")
                .subServerName("BW02")
                .teamCurrentSize(3)
                .teamMaxSize(4)
                .build()
            );
        });
    }

    @Override
    public void resetServerRichPresenceExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(this.richPresenceModule::resetServerRichPresence);
    }

}
