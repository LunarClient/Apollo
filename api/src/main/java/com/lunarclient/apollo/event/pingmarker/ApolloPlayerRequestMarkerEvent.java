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
package com.lunarclient.apollo.event.pingmarker;

import com.lunarclient.apollo.common.location.ApolloLocation;
import com.lunarclient.apollo.event.EventCancellable;
import com.lunarclient.apollo.module.pingmarker.PingMarkerType;
import com.lunarclient.apollo.player.ApolloPlayer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.jetbrains.annotations.Nullable;

/**
 * Represents an event that is fired when the player requests a ping marker.
 *
 * @since 1.1.9
 */
@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class ApolloPlayerRequestMarkerEvent implements EventCancellable {

    /**
     * The player sending the ping request.
     *
     * @return the source player
     * @since 1.1.9
     */
    ApolloPlayer player;

    /**
     * The {@link PingMarkerType} for this request.
     *
     * @return the ping marker type
     * @since 1.1.9
     */
    @Nullable PingMarkerType type;

    /**
     * The source {@link ApolloLocation} for this request.
     *
     * @return the source location
     * @since 1.1.9
     */
    ApolloLocation source;

    /**
     * The target {@link ApolloLocation} for this request.
     *
     * @return the target location
     * @since 1.1.9
     */
    ApolloLocation target;

    @NonFinal @Setter boolean cancelled;

}
