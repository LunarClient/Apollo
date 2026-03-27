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
package com.lunarclient.apollo.example.api.module;

import com.google.common.collect.Lists;
import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.common.icon.ResourceLocationIcon;
import com.lunarclient.apollo.example.module.impl.ServerLinkExample;
import com.lunarclient.apollo.module.serverlink.ServerLink;
import com.lunarclient.apollo.module.serverlink.ServerLinkModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.util.Optional;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

public class ServerLinkApiExample extends ServerLinkExample {

    private final ServerLinkModule serverLinkModule = Apollo.getModuleManager().getModule(ServerLinkModule.class);

    @Override
    public void overrideServerLinkResourceExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresent(apolloPlayer -> {
            this.serverLinkModule.overrideServerLinkResource(apolloPlayer, ResourceLocationIcon.builder()
                .resourceLocation("lunar:logo/logo-100x100.png")
                .build());
        });
    }

    @Override
    public void resetServerLinkResourceExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(this.serverLinkModule::resetServerLinkResource);
    }

    @Override
    public void addServerLinkExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresent(apolloPlayer -> {
            this.serverLinkModule.addServerLink(apolloPlayer, Lists.newArrayList(
                ServerLink.builder()
                    .id("website")
                    .displayName(Component.text("Website", NamedTextColor.LIGHT_PURPLE))
                    .url("https://www.lunarclient.com/")
                    .build(),
                ServerLink.builder()
                    .id("support")
                    .displayName(Component.text("Support", NamedTextColor.AQUA))
                    .url("https://support.lunarclient.com/")
                    .build(),
                ServerLink.builder()
                    .id("status")
                    .displayName(Component.text("Status", NamedTextColor.RED))
                    .url("https://status.lunarclient.com/")
                    .build()
            ));
        });
    }

    @Override
    public void removeServerLinkExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresent(apolloPlayer -> {
            this.serverLinkModule.removeServerLink(apolloPlayer, Lists.newArrayList(
                "website", "support", "status"
            ));
        });
    }

    @Override
    public void resetServerLinksExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(this.serverLinkModule::resetServerLinks);
    }

}
