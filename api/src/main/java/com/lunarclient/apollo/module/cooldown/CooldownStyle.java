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
package com.lunarclient.apollo.module.cooldown;

import java.awt.Color;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

/**
 * Represents the {@link Cooldown} style, allowing customization of the circle start, end, edge & text color.
 *
 * @since 1.2.5
 */
@Getter
@Builder
public final class CooldownStyle {

    /**
     * Returns the cooldown circle start {@link Color}.
     *
     * @return the circle start color
     * @since 1.2.5
     */
    @Nullable Color circleStartColor;

    /**
     * Returns the cooldown circle end {@link Color}.
     *
     * @return the circle end color
     * @since 1.2.5
     */
    @Nullable Color circleEndColor;

    /**
     * Returns the cooldown circle edge {@link Color}.
     *
     * @return the circle edge color
     * @since 1.2.5
     */
    @Nullable Color circleEdgeColor;

    /**
     * Returns the cooldown text {@link Color}.
     *
     * @return the text color
     * @since 1.2.5
     */
    @Nullable Color textColor;

}
