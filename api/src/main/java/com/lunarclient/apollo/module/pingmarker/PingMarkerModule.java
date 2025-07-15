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

import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.module.ModuleDefinition;
import com.lunarclient.apollo.recipients.Recipients;
import java.util.UUID;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the ping marker module.
 *
 * @since 1.1.9
 */
@ApiStatus.NonExtendable
@ModuleDefinition(id = "ping_marker", name = "Ping Marker")
public abstract class PingMarkerModule extends ApolloModule {

    /**
     * Sends the {@link PingMarkerType} to the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param type       the marker type
     * @since 1.1.9
     */
    public abstract void setMarkerType(Recipients recipients, PingMarkerType type);

    /**
     * Removes the {@link PingMarkerType} from the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param name       the marker type name
     * @since 1.1.9
     */
    public abstract void removeMarkerType(Recipients recipients, String name);

    /**
     * Removes the {@link PingMarkerType} from the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param type       the marker type
     * @since 1.1.9
     */
    public abstract void removeMarkerType(Recipients recipients, PingMarkerType type);

    /**
     * Resets all {@link PingMarkerType}s for the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @since 1.1.9
     */
    public abstract void resetMarkerType(Recipients recipients);

    /**
     * Displays the {@link PingMarker} to the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param marker     the marker
     * @since 1.1.9
     */
    public abstract void displayMarker(Recipients recipients, PingMarker marker);

    /**
     * Removes the {@link PingMarker} from the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param markerId the marker id
     * @since 1.1.9
     */
    public abstract void removeMarker(Recipients recipients, UUID markerId);

    /**
     * Removes the {@link PingMarker} from the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param marker     the marker
     * @since 1.1.9
     */
    public abstract void removeMarker(Recipients recipients, PingMarker marker);

    /**
     * Resets all {@link PingMarker}s for the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @since 1.1.9
     */
    public abstract void resetMarkers(Recipients recipients);

}
