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
package com.lunarclient.apollo.module.border;

import com.lunarclient.apollo.common.cuboid.Cuboid2D;
import java.awt.Color;
import lombok.Builder;
import lombok.Getter;

/**
 * Represents a border which can be shown on the client.
 *
 * @since 1.0.0
 */
@Getter
@Builder
public final class Border {

    /**
     * Returns the border {@link String} id.
     *
     * @return the border id
     * @since 1.0.0
     */
    String id;

    /**
     * Returns the border {@link String} world name.
     *
     * @return the border world name
     * @since 1.0.0
     */
    String world;

    /**
     * Returns the border {@link Boolean} cancel entry state.
     *
     * @return the border cancel entry state
     * @since 1.0.0
     */
    boolean cancelEntry;

    /**
     * Returns the border {@link Boolean} cancel exit state.
     *
     * @return the border cancel exit state
     * @since 1.0.0
     */
    boolean cancelExit;

    /**
     * Returns the border {@link Boolean} can shrink or expand state.
     *
     * @return the border can shrink or expand state
     * @since 1.0.0
     */
    boolean canShrinkOrExpand;

    /**
     * Returns the border {@link Color}.
     *
     * @return the border color
     * @since 1.0.0
     */
    Color color;

    /**
     * Returns the border {@link Cuboid2D}.
     *
     * @return the border bounds
     * @since 1.0.0
     */
    Cuboid2D bounds;

    /**
     * Returns the border {@link Integer} duration represented in ticks.
     *
     * @return the border duration
     * @since 1.0.0
     */
    int durationTicks;

}
