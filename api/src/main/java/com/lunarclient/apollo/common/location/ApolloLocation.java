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
package com.lunarclient.apollo.common.location;

import lombok.Builder;
import lombok.Getter;

/**
 * Represents a location in the world.
 *
 * @since 1.0.0
 */
@Getter
@Builder
public final class ApolloLocation {

    /**
     * Returns the world name for this location.
     *
     * @return the world name
     * @since 1.0.0
     */
    String world;

    /**
     * Returns the {@code double} X coordinate for this location.
     *
     * @return the x coordinate
     * @since 1.0.0
     */
    double x;

    /**
     * Returns the {@code double} Y coordinate for this location.
     *
     * @return the y coordinate
     * @since 1.0.0
     */
    double y;

    /**
     * Returns the {@code double} Z coordinate for this location.
     *
     * @return the z coordinate
     * @since 1.0.0
     */
    double z;

}
