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
package com.lunarclient.apollo.common.anticheat;

import com.lunarclient.apollo.common.location.ApolloPlayerLocation;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

/**
 * Represents a player info for the apollo anti-cheat module.
 *
 * @since 1.0.0
 */
@Getter
@Builder
public class PlayerInfo {

    /**
     * The UUID of this team member.
     *
     * @return the player uuid
     * @since 1.0.0
     */
    UUID playerUuid;

    /**
     * Returns the {@link ApolloPlayerLocation} for this player.
     *
     * @return the player location
     * @since 1.0.0
     */
    ApolloPlayerLocation location;

    /**
     * Returns the player {@link Boolean} sneaking state.
     *
     * @return the sneaking state
     * @since 1.0.0
     */
    boolean sneaking;

    /**
     * Returns the player {@link Boolean} sprinting state.
     *
     * @return the sprinting state
     * @since 1.0.0
     */
    boolean sprinting;

    /**
     * Returns the player {@link Long} ping in milliseconds.
     *
     * @return the ping
     * @since 1.0.0
     */
    long ping;

}
