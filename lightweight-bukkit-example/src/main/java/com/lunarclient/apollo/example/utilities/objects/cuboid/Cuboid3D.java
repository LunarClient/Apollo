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
package com.lunarclient.apollo.example.utilities.objects.cuboid;

import lombok.Builder;
import lombok.Getter;

/**
 * Represents a 3D cuboid.
 *
 * @since 1.0.0
 */
@Getter
@Builder
public final class Cuboid3D {

    /**
     * Returns the cuboid {@link Double} start x.
     *
     * @return the cuboid start x
     * @since 1.0.0
     */
    double minX;

    /**
     * Returns the cuboid {@link Double} start y.
     *
     * @return the cuboid start y
     * @since 1.0.0
     */
    double minY;

    /**
     * Returns the cuboid {@link Double} start z.
     *
     * @return the cuboid start z
     * @since 1.0.0
     */
    double minZ;

    /**
     * Returns the cuboid {@link Double} end x.
     *
     * @return the cuboid end x
     * @since 1.0.0
     */
    double maxX;

    /**
     * Returns the cuboid {@link Double} end y.
     *
     * @return the cuboid end y
     * @since 1.0.0
     */
    double maxY;

    /**
     * Returns the cuboid {@link Double} end z.
     *
     * @return the cuboid end z
     * @since 1.0.0
     */
    double maxZ;

}
