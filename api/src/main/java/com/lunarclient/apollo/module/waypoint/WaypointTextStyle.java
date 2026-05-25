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
package com.lunarclient.apollo.module.waypoint;

import lombok.Builder;
import lombok.Getter;

/**
 * Overrides for a waypoint's on-screen label, icons and distance HUD on the Lunar client.
 *
 * @since 1.2.7
 */
@Getter
@Builder
public final class WaypointTextStyle {

    /**
     * Whether the waypoint label (text and box) is drawn at all.
     *
     * @since 1.2.7
     */
    @Builder.Default
    boolean showText = true;

    /**
     * Restricts the waypoint label drawing to when the player is looking roughly toward the waypoint.
     *
     * @since 1.2.7
     */
    @Builder.Default
    boolean onlyShowTextWhenLookingNear = false;

    /**
     * Whether the waypoint icons are drawn.
     *
     * @since 1.2.7
     */
    @Builder.Default
    boolean showIcons = false;

    /**
     * The waypoint scale applied to the label icon.
     *
     * <p>Accepts {@code 0.1F} to {@code 3.0F}</p>
     *
     * @since 1.2.7
     */
    @Builder.Default
    float textIconScale = 1.5F;

    /**
     * The waypoint scale applied to the main label text.
     *
     * <p>Accepts {@code 0.1F} to {@code 2.0F}</p>
     *
     * @since 1.2.7
     */
    @Builder.Default
    float labelScale = 1.0F;

    /**
     * The waypoint padding of the label background box.
     *
     * <p>Accepts {@code 1.0F} to {@code 8.0F}</p>
     *
     * @since 1.2.7
     */
    @Builder.Default
    float boxPadding = 4.0F;

    /**
     * Whether a border is drawn around the label background box.
     *
     * @since 1.2.7
     */
    @Builder.Default
    boolean boxBorders = true;

    /**
     * Whether a shadow is drawn behind the label text.
     *
     * @since 1.2.7
     */
    @Builder.Default
    boolean textShadow = false;

    /**
     * Whether the distance from the player to the waypoint is shown next to the label.
     *
     * @since 1.2.7
     */
    @Builder.Default
    boolean showDistance = true;

}
