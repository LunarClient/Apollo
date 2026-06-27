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
package com.lunarclient.apollo.module.marker;

import com.lunarclient.apollo.module.marker.display.MarkerDescriptionDisplay;
import com.lunarclient.apollo.module.marker.display.MarkerDisplayCondition;
import com.lunarclient.apollo.module.marker.display.MarkerOwnerDisplay;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * Overrides for a marker's appearance, owner/description display and text on the Lunar client.
 *
 * @since 1.2.8
 */
@Getter
@Builder
public final class MarkerStyle {

    /**
     * The scale applied to the marker.
     *
     * <p>Accepts {@code 0.5F} to {@code 2.0F}.</p>
     *
     * @since 1.2.8
     */
    @Builder.Default
    float scale = 1.0F;

    /**
     * Whether the marker plays its hover animations.
     *
     * @since 1.2.8
     */
    @Builder.Default
    boolean animateMarkerOnHover = true;

    /**
     * Whether the marker uses the compact single-row layout.
     *
     * <p>When {@code true}, {@link #ownerDisplay}, {@link #descriptionDisplay} and
     * {@link #ownerSuffix} are ignored; the compact layout always uses the owner head,
     * description icon and shows no suffix.</p>
     *
     * @since 1.2.8
     */
    @Builder.Default
    boolean compactMode = false;

    /**
     * Whether a shadow is drawn behind the marker's text.
     *
     * @since 1.2.8
     */
    @Builder.Default
    boolean textShadow = true;

    /**
     * The suffix appended after the owner name.
     *
     * <p>Set to an empty {@link String} to show no suffix.</p>
     *
     * @since 1.2.8
     */
    @Builder.Default
    @NotNull String ownerSuffix = "'s Marker";

    /**
     * How the marker's owner is displayed.
     *
     * @since 1.2.8
     */
    @Builder.Default
    @NotNull MarkerOwnerDisplay ownerDisplay = MarkerOwnerDisplay.HEAD;

    /**
     * When the marker's owner is shown.
     *
     * @since 1.2.8
     */
    @Builder.Default
    @NotNull MarkerDisplayCondition showOwner = MarkerDisplayCondition.ALWAYS;

    /**
     * When the marker's coordinates are shown.
     *
     * @since 1.2.8
     */
    @Builder.Default
    @NotNull MarkerDisplayCondition showCoordinates = MarkerDisplayCondition.NEVER;

    /**
     * When the distance from the player to the marker is shown.
     *
     * @since 1.2.8
     */
    @Builder.Default
    @NotNull MarkerDisplayCondition showDistance = MarkerDisplayCondition.HOVER;

    /**
     * When the marker's description (what was marked) is shown.
     *
     * @since 1.2.8
     */
    @Builder.Default
    @NotNull MarkerDisplayCondition showDescription = MarkerDisplayCondition.HOVER;

    /**
     * How the marker's description is displayed.
     *
     * @since 1.2.8
     */
    @Builder.Default
    @NotNull MarkerDescriptionDisplay descriptionDisplay = MarkerDescriptionDisplay.ICON;

}
