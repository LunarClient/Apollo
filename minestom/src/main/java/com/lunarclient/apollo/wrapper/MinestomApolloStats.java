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

import com.lunarclient.apollo.ApolloMinestomPlatform;
import com.lunarclient.apollo.stats.ApolloPluginDescription;
import com.lunarclient.apollo.stats.ApolloStats;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import javax.imageio.ImageIO;
import net.minestom.server.Auth;
import net.minestom.server.Git;
import net.minestom.server.MinecraftServer;

/**
 * The Minestom implementation of {@link ApolloStats}.
 *
 * @since 1.1.9
 */
public class MinestomApolloStats implements ApolloStats {

    @Override
    public boolean isOnlineMode() {
        return MinecraftServer.process().auth() instanceof Auth.Online;
    }

    @Override
    public String getIcon() {
        try (InputStream stream = ApolloMinestomPlatform.class.getResourceAsStream("/favicon.png")) {
            if (stream == null) {
                return null;
            }

            BufferedImage image = ImageIO.read(stream);
            if (image == null) {
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
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getVersion() {
        return MinecraftServer.VERSION_NAME;
    }

    @Override
    public List<ApolloPluginDescription> getPlugins() {
        return Collections.emptyList();
    }

    @Override
    public String getPlatformSubtype() {
        return "Minestom";
    }

    @Override
    public String getPlatformVersion() {
        return Git.version();
    }

    @Override
    public int getTotalPlayers() {
        return MinecraftServer.getConnectionManager().getOnlinePlayerCount();
    }

}
