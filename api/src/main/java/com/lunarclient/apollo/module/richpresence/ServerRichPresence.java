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
package com.lunarclient.apollo.module.richpresence;

import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a server rich presence for the rich presence module.
 *
 * @since 1.1.2
 */
@Getter
@Builder
public class ServerRichPresence {

    /**
     * Returns the {@link String} name of the game the player is playing on (e.g. 'BedWars').
     *
     * @return the game name
     * @since 1.1.2
     */
    @Nullable String gameName;

    /**
     * Returns the {@link String} variant of the game the player is playing on (e.g. 'Solo').
     *
     * @return the game variant name
     * @since 1.1.2
     */
    @Nullable String gameVariantName;

    /**
     * Returns the {@link String} state of the current game (e.g. 'In Game').
     *
     * @return the game state
     * @since 1.1.2
     */
    @Nullable String gameState;

    /**
     * Returns {@link String} what the player is currently doing in the game (e.g. 'Spectating', 'Playing', 'Crafting').
     *
     * @return the player state
     * @since 1.1.2
     */
    @Nullable String playerState;

    /**
     * Returns the {@link String} name of the map the player is playing on (e.g. 'Winter').
     *
     * @return the map name
     * @since 1.1.2
     */
    @Nullable String mapName;

    /**
     * Returns the {@link String} sub server name of the server the player is playing on (e.g. 'BW02').
     *
     * @return the sub server
     * @since 1.1.2
     */
    @Nullable String subServerName;

    /**
     * Returns the {@link Integer} current size of the player's team.
     *
     * @return the current team size
     * @since 1.1.2
     */
    int teamCurrentSize;

    /**
     * Returns the {@link Integer} max size of the player's team.
     *
     * @return the max team size
     * @since 1.1.2
     */
    int teamMaxSize;

}
