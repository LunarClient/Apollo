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
package com.lunarclient.apollo.wrapper;

import com.lunarclient.apollo.stats.ApolloPluginDescription;
import com.lunarclient.apollo.stats.ApolloStats;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import net.md_5.bungee.api.Favicon;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

/**
 * The Bungee implementation of {@link ApolloStats}.
 *
 * @since 1.0.0
 */
public class BungeeApolloStats implements ApolloStats {

    @Override
    public boolean isOnlineMode() {
        return ProxyServer.getInstance().getConfig().isOnlineMode();
    }

    @Override
    public String getIcon() {
        Favicon favicon = ProxyServer.getInstance().getConfig().getFaviconObject();
        return favicon == null ? null : favicon.getEncoded();
    }

    @Override
    public String getVersion() {
        try {
            return ProxyServer.getInstance().getVersion().split(":")[2];
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public List<ApolloPluginDescription> getPlugins() {
        return ProxyServer.getInstance().getPluginManager().getPlugins()
            .stream().map(Plugin::getDescription)
            .filter(plugin -> !plugin.getAuthor().equals("SpigotMC"))
            .map(description -> ApolloPluginDescription.builder()
                .name(description.getName())
                .description(description.getDescription())
                .authors(Collections.singletonList(description.getAuthor()))
                .version(description.getVersion())
                .build())
            .collect(Collectors.toList());
    }

    @Override
    public String getPlatformSubtype() {
        return "BungeeCord";
    }

    @Override
    public String getPlatformVersion() {
        return ProxyServer.getInstance().getVersion();
    }

    @Override
    public int getTotalPlayers() {
        return ProxyServer.getInstance().getOnlineCount();
    }

}
