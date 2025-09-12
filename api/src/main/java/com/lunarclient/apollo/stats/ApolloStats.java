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
package com.lunarclient.apollo.stats;

import java.util.List;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Range;

/**
 * Represents Apollo tracked stats.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
public interface ApolloStats {

    /**
     * Gets the servers online mode state.
     *
     * @return the servers online mode state.
     * @since 1.0.0
     */
    boolean isOnlineMode();

    /**
     * Gets the servers icon (Base64).
     *
     * @return the servers icon
     * @since 1.0.0
     */
    String getIcon();

    /**
     * Gets the servers version.
     *
     * @return the servers version
     * @since 1.0.0
     */
    String getVersion();

    /**
     * Gets the servers plugin list.
     *
     * @return the servers plugin list
     * @since 1.0.0
     */
    List<ApolloPluginDescription> getPlugins();

    /**
     * Gets the servers platform subtype (Bukkit, Folia, Minestom, BungeeCord, Velocity...).
     *
     * @return the servers platform subtype
     * @since 1.0.0
     */
    String getPlatformSubtype();

    /**
     * Gets the servers platform version.
     *
     * @return the servers platform version
     * @since 1.0.0
     */
    String getPlatformVersion();

    /**
     * Gets the servers total players.
     *
     * @return the servers total players
     * @since 1.0.0
     */
    @Range(from = 0, to = Integer.MAX_VALUE) int getTotalPlayers();

}
