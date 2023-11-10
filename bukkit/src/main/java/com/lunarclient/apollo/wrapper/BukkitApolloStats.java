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
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

/**
 * The Bukkit implementation of {@link ApolloStats}.
 *
 * @since 1.0.0
 */
public class BukkitApolloStats implements ApolloStats {

    @Override
    public boolean isOnlineMode() {
        return Bukkit.getServer().getOnlineMode();
    }

    @Override
    public String getIcon() {
        File icon = new File(new File("."), "server-icon.png");

        if (!icon.isFile()) {
            return null;
        }

        BufferedImage image;
        try {
            image = ImageIO.read(icon);
        } catch (IOException e) {
            return null;
        }

        if (image.getWidth() != 64 || image.getHeight() != 64) {
            return null;
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, "PNG", outputStream);
        } catch (IOException e) {
            return null;
        }

        byte[] bytes = outputStream.toByteArray();
        return Base64.getEncoder().encodeToString(bytes);
    }

    @Override
    public String getVersion() {
        return Bukkit.getServer().getBukkitVersion();
    }

    @Override
    public List<ApolloPluginDescription> getPlugins() {
        return Arrays.stream(Bukkit.getPluginManager().getPlugins())
            .map(Plugin::getDescription)
            .map(description -> ApolloPluginDescription.builder()
                .name(description.getName())
                .description(description.getDescription())
                .authors(description.getAuthors())
                .version(description.getVersion())
                .build())
            .collect(Collectors.toList());
    }

    @Override
    public String getPlatformSubtype() {
        return "Bukkit";
    }

    @Override
    public String getPlatformVersion() {
        return Bukkit.getServer().getVersion();
    }

    @Override
    public int getTotalPlayers() {
        return Bukkit.getOnlinePlayers().size();
    }

}
