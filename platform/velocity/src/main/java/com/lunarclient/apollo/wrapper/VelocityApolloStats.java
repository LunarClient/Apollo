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

import com.lunarclient.apollo.ApolloVelocityPlatform;
import com.lunarclient.apollo.stats.ApolloPluginDescription;
import com.lunarclient.apollo.stats.ApolloStats;
import com.velocitypowered.api.plugin.PluginContainer;
import com.velocitypowered.api.util.Favicon;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The Velocity implementation of {@link ApolloStats}.
 *
 * @since 1.0.0
 */
public class VelocityApolloStats implements ApolloStats {

    @Override
    public boolean isOnlineMode() {
        return ApolloVelocityPlatform.getInstance().getServer().getConfiguration().isOnlineMode();
    }

    @Override
    public String getIcon() {
        Optional<Favicon> favicon = ApolloVelocityPlatform.getInstance().getServer().getConfiguration().getFavicon();
        return favicon.map(Favicon::getBase64Url).orElse(null);
    }

    @Override
    public String getVersion() {
        return null;
    }

    @Override
    public List<ApolloPluginDescription> getPlugins() {
        return ApolloVelocityPlatform.getInstance().getServer().getPluginManager().getPlugins()
            .stream().map(PluginContainer::getDescription)
            .map(description -> ApolloPluginDescription.builder()
                .name(description.getName().orElse(null))
                .description(description.getDescription().orElse(null))
                .authors(description.getAuthors())
                .version(description.getVersion().orElse(null))
                .build())
            .collect(Collectors.toList());
    }

    @Override
    public String getPlatformSubtype() {
        return "Velocity";
    }

    @Override
    public String getPlatformVersion() {
        return ApolloVelocityPlatform.getInstance().getServer().getVersion().getVersion();
    }

    @Override
    public int getTotalPlayers() {
        return ApolloVelocityPlatform.getInstance().getServer().getPlayerCount();
    }

}
