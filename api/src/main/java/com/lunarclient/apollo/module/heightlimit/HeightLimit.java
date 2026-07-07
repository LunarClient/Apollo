/*
 * This file is part of Apollo, licensed under the MIT License.
 *
 * Copyright (c) 2026 Moonsworth
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
package com.lunarclient.apollo.module.heightlimit;

import lombok.Builder;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

/**
 * Represents a height limit which can be shown on the client.
 *
 * @since 1.2.9
 */
@Getter
@Builder
public final class HeightLimit {

    /**
     * Returns the height limit {@link String} world name.
     *
     * @return the height limit world name
     * @since 1.2.9
     */
    String world;

    /**
     * Returns the height limit {@link Integer} Y level where block placement
     * is denied.
     *
     * <p>The highest buildable layer is {@code limit - 1}.</p>
     *
     * @return the height limit
     * @since 1.2.9
     */
    @Range(from = 1, to = Integer.MAX_VALUE) int limit;

    /**
     * Returns the height limit {@link Component} display name.
     *
     * <p>Shown on the client's height limit HUD.</p>
     *
     * @return the height limit display name
     * @since 1.2.9
     */
    @Nullable Component displayName;

}
