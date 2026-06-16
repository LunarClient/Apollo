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

import com.lunarclient.apollo.common.location.ApolloLocation;
import com.lunarclient.apollo.module.marker.display.MarkerFlag;
import com.lunarclient.apollo.module.marker.target.BlockMarkerTarget;
import com.lunarclient.apollo.module.marker.target.EntityMarkerTarget;
import com.lunarclient.apollo.module.marker.target.ItemMarkerTarget;
import com.lunarclient.apollo.module.marker.target.MarkerTarget;
import com.lunarclient.apollo.module.marker.target.PlayerMarkerTarget;
import java.awt.Color;
import java.time.Duration;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a marker which can be shown on the client.
 *
 * @since 1.2.8
 */
@Getter
@Builder
public final class Marker {

    /**
     * Returns the marker {@link String} id.
     *
     * @return the marker id
     * @since 1.2.8
     */
    @NotNull String id;

    /**
     * Returns the marker {@link ApolloLocation}.
     *
     * @return the marker location
     * @since 1.2.8
     */
    @NotNull ApolloLocation location;

    /**
     * Returns the marker owner's {@link UUID}.
     *
     * <p>Used to show the owner head.</p>
     *
     * @return the owner uuid
     * @since 1.2.8
     */
    @NotNull UUID ownerId;

    /**
     * Returns the marker owner's {@link String} name.
     *
     * @return the owner name
     * @since 1.2.8
     */
    @NotNull String ownerName;

    /**
     * Returns the {@link MarkerFlag} (icon shape and base color) of this marker.
     *
     * @return the marker flag
     * @since 1.2.8
     */
    @NotNull MarkerFlag flag;

    /**
     * Returns the {@link Color} override for this marker's {@link #flag}.
     *
     * <p>Leave {@code null} to use the player's own configured color for the
     * selected flag.</p>
     *
     * @return the flag color override, or {@code null} to defer to the player's setting
     * @since 1.2.8
     */
    @Builder.Default
    @Nullable Color color = null;

    /**
     * Returns the {@link MarkerTarget} describing what this marker marks.
     *
     * <p>Drives the description icon and text shown on the client. One of
     * {@link ItemMarkerTarget}, {@link BlockMarkerTarget}, {@link EntityMarkerTarget}
     * or {@link PlayerMarkerTarget}.</p>
     *
     * @return the marker target
     * @since 1.2.8
     */
    @NotNull MarkerTarget target;

    /**
     * Returns the {@link Duration} the marker remains visible.
     *
     * <p>Leave {@code null} to defer to the player's configured marker
     * duration.</p>
     *
     * @return the duration, or {@code null} to defer to the player's setting
     * @since 1.2.8
     */
    @Builder.Default
    @Nullable Duration duration = null;

    /**
     * Returns whether a popup notification is shown when this marker first appears.
     *
     * @return whether an in-game notification is shown
     * @since 1.2.8
     */
    @Builder.Default
    boolean inGameNotification = false;

    /**
     * Returns whether a chat message is sent when this marker first appears.
     *
     * @return whether an in-game chat message is sent
     * @since 1.2.8
     */
    @Builder.Default
    boolean chatNotify = false;

    /**
     * Returns whether the player can middle-click to remove this marker.
     *
     * @return the middle-click removal state
     * @since 1.2.8
     */
    @Builder.Default
    boolean middleClickRemove = true;

    /**
     * Returns the {@link MarkerStyle} overrides applied to this marker.
     *
     * <p>Set to a built {@link MarkerStyle} to drive the marker's appearance
     * from the server; leave {@code null} to defer entirely to the player's
     * own Markers mod settings.</p>
     *
     * @return the style overrides, or {@code null} when no override is sent
     * @since 1.2.8
     */
    @Builder.Default
    @Nullable MarkerStyle style = null;

}
