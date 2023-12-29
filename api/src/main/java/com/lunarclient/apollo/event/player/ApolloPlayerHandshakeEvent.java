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
package com.lunarclient.apollo.event.player;

import com.lunarclient.apollo.client.mod.LunarClientMod;
import com.lunarclient.apollo.client.version.LunarClientVersion;
import com.lunarclient.apollo.client.version.MinecraftVersion;
import com.lunarclient.apollo.event.Event;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.util.List;
import lombok.Value;

/**
 * Represents an event that is fired when the client sends a PlayerHandshakeMessage.
 *
 * @since 1.0.6
 */
@Value
public class ApolloPlayerHandshakeEvent implements Event {

    /**
     * The player that sent the packet.
     *
     * @return the player
     * @since 1.0.6
     */
    ApolloPlayer player;

    /**
     * The {@link MinecraftVersion} the player is running.
     *
     * @return the minecraft version
     * @since 1.0.6
     */
    MinecraftVersion minecraftVersion;

    /**
     * The {@link LunarClientVersion} the player is running.
     *
     * @return the lunar client version
     * @since 1.0.6
     */
    LunarClientVersion lunarClientVersion;

    /**
     * A {@link List} of {@link LunarClientMod} the player has installed.
     *
     * @return the installed mods
     * @since 1.0.6
     */
    List<LunarClientMod> installedMods;

}
