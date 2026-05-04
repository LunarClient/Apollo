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
package com.lunarclient.apollo.module.stopwatch;

import com.lunarclient.apollo.common.location.HudPosition;
import java.awt.Color;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a stopwatch which can be displayed on the client HUD.
 *
 * @since 1.2.6
 */
@Getter
@Builder
public final class Stopwatch {

    /**
     * Returns the stopwatch {@link String} id.
     *
     * @return the stopwatch id
     * @since 1.2.6
     */
    String id;

    /**
     * Returns the stopwatch {@link String} name.
     *
     * @return the stopwatch name
     * @since 1.2.6
     */
    String name;

    /**
     * Returns the stopwatch {@code boolean} reset on start.
     *
     * <p>If {@code true}, elapsed time is reset on each start.</p>
     *
     * @return whether to reset on start
     * @since 1.2.6
     */
    boolean resetOnStart;

    /**
     * Returns the stopwatch {@code boolean} prevent modification.
     *
     * <p>If {@code true}, the user cannot modify the options for this
     * stopwatch on the client side.</p>
     *
     * @return whether modification is prevented
     * @since 1.2.6
     */
    boolean preventModification;

    /**
     * Returns the stopwatch {@code boolean} hide when stopped.
     *
     * <p>If {@code true}, the stopwatch is hidden from the HUD when stopped.</p>
     *
     * @return whether to hide when stopped
     * @since 1.2.6
     */
    boolean hideWhenStopped;

    /**
     * Returns the stopwatch {@link String} display format.
     *
     * <p>A format string (e.g. {@code "mm:ss"}), or {@code null}
     * for the default display format.</p>
     *
     * @return the display format string
     * @since 1.2.6
     */
    @Nullable String displayFormat;

    /**
     * Returns the stopwatch {@link Color} text color.
     *
     * <p>If {@code null}, the default color (white) is used.</p>
     *
     * @return the text color
     * @since 1.2.6
     */
    @Nullable Color textColor;

    /**
     * Returns the stopwatch {@link HudPosition} HUD position.
     *
     * <p>If {@code null}, the stopwatch is auto-stacked at the default position.</p>
     *
     * @return the HUD position
     * @since 1.2.6
     */
    @Nullable HudPosition hudPosition;

}
