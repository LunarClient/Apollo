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
package com.lunarclient.apollo.event.packetenrichment.world;

import com.lunarclient.apollo.event.Event;
import com.lunarclient.apollo.module.packetenrichment.PlayerInfo;
import lombok.Value;

/**
 * Represents an event that is when a player uses an item (1.16.1+).
 *
 * @since 1.0.7
 */
@Value
public class ApolloPlayerUseItemEvent implements Event {

    /**
     * The {@code long} representing the unix timestamp
     * when the packet was created.
     *
     * @return the unix timestamp
     * @since 1.0.7
     */
    long instantiationTimeMs;

    /**
     * The player's {@link PlayerInfo} information.
     *
     * @return the player info
     * @since 1.0.7
     */
    PlayerInfo playerInfo;

    /**
     * Whether {@code boolean} the used item is in the main hand.
     *
     * @return the main hand
     * @since 1.0.7
     */
    boolean mainHand;

}
