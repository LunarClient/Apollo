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
package com.lunarclient.apollo.module.playerping;

import com.lunarclient.apollo.common.location.ApolloLocation;
import java.awt.Color;
import java.time.Duration;
import lombok.Builder;
import lombok.Getter;

/**
 * Represents a player ping which can be shown on the client.
 *
 * @since 1.1.2
 */
@Getter
@Builder
public final class PlayerPing {

    /**
     * Returns the player ping {@link String} id.
     *
     * @return the player ping id
     * @since 1.1.2
     */
    String id;

    /**
     * Returns the player ping {@link Color}.
     *
     * @return the player ping color
     * @since 1.1.2
     */
    Color color;

    /**
     * Returns the player ping {@link ApolloLocation}.
     *
     * @return the player ping location
     * @since 1.1.2
     */
    ApolloLocation location;

    /**
     * Returns the player ping {@link Duration} display time.
     *
     * @return the player ping display time
     * @since 1.1.2
     */
    Duration displayTime;

}
