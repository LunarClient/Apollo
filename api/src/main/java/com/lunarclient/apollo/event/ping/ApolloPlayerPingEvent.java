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
package com.lunarclient.apollo.event.ping;

import com.lunarclient.apollo.event.Event;
import com.lunarclient.apollo.module.playerping.PlayerPing;
import com.lunarclient.apollo.player.ApolloPlayer;
import lombok.Value;

/**
 * Represents an event that is fired when a player ping message is received from a player.
 *
 * @since 1.1.2
 */
@Value
public class ApolloPlayerPingEvent implements Event {

    /**
     * The player that sent the packet.
     *
     * @return the player
     * @since 1.1.2
     */
    ApolloPlayer player;

    /**
     * The {@link PlayerPing} the player sent.
     *
     * @return the ping
     * @since 1.1.2
     */
    PlayerPing ping;

}
