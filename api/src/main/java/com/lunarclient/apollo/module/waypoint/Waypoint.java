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
package com.lunarclient.apollo.module.waypoint;

import com.lunarclient.apollo.common.location.ApolloBlockLocation;
import java.awt.Color;
import lombok.Builder;
import lombok.Getter;

/**
 * Represents a waypoint which can be shown on the client.
 *
 * @since 1.0.0
 */
@Getter
@Builder
public final class Waypoint {

    /**
     * Returns the waypoint {@link String} name.
     *
     * @return the waypoint name
     * @since 1.0.0
     */
    String name;

    /**
     * Returns the waypoint {@link ApolloBlockLocation}.
     *
     * @return the waypoint block location
     * @since 1.0.0
     */
    ApolloBlockLocation location;

    /**
     * Returns the waypoint {@link Color}.
     *
     * @return the waypoint color
     * @since 1.0.0
     */
    Color color;

    /**
     * Returns the waypoint {@link Boolean} prevent removal state.
     *
     * @return the waypoint forced state
     * @since 1.0.0
     */
    boolean preventRemoval;

    /**
     * Returns the waypoint {@link Boolean} visible state.
     *
     * @return the waypoint visibility state
     * @since 1.0.0
     */
    boolean visible;

}
