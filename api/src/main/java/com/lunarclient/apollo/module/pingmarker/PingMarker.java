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
package com.lunarclient.apollo.module.pingmarker;

import com.lunarclient.apollo.common.icon.Icon;
import com.lunarclient.apollo.common.location.ApolloLocation;
import java.awt.Color;
import java.time.Duration;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a ping marker which can be shown on the client.
 *
 * @since 1.1.9
 */
@Getter
@Builder
public final class PingMarker {

    /**
     * Returns the marker {@link UUID}.
     *
     * @return the ping marker id
     * @since 1.1.9
     */
    UUID id;

    /**
     * Returns the {@link PingMarkerType}.
     *
     * @return the ping marker type
     * @since 1.1.9
     */
    @Nullable PingMarkerType type;

    /**
     * Returns the marker {@link ApolloLocation}.
     *
     * @return the ping marker location
     * @since 1.1.9
     */
    ApolloLocation location;

    /**
     * Returns the marker {@link Color}.
     *
     * @return the ping marker color
     * @since 1.1.9
     */
    Color color;

    /**
     * Returns the marker {@link Duration}.
     *
     * @return the ping marker duration
     * @since 1.1.9
     */
    Duration duration;

    /**
     * Returns whether the marker should animate.
     *
     * @return the ping marker focus state
     * @since 1.1.9
     */
    boolean focus;

    /**
     * Returns the ping marker {@link Icon}.
     *
     * <p>Can be any of the icons found in {@link com.lunarclient.apollo.common.icon} package,
     * for the most common use case, use {@link com.lunarclient.apollo.common.icon.ItemStackIcon}.</p>
     *
     * @return the ping marker icon
     * @since 1.1.9
     */
    Icon icon;

}
