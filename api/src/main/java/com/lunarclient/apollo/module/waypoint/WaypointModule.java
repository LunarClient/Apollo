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
package com.lunarclient.apollo.module.waypoint;

import com.lunarclient.apollo.common.location.ApolloBlockLocation;
import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.module.ModuleDefinition;
import com.lunarclient.apollo.option.ListOption;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.recipients.Recipients;
import io.leangen.geantyref.TypeToken;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the waypoint module.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
@ModuleDefinition(id = "waypoint", name = "Waypoint")
public abstract class WaypointModule extends ApolloModule {

    private static final Waypoint SPAWN_WAYPOINT = Waypoint.builder()
        .name("Spawn")
        .location(ApolloBlockLocation.builder()
            .world("world")
            .x(0)
            .y(100)
            .z(0)
            .build())
        .color(Color.RED)
        .preventRemoval(false)
        .hidden(false)
        .build();

    /**
     * Returns the default list of waypoints to show the player.
     *
     * @since 1.0.0
     */
    public static final ListOption<Waypoint> DEFAULT_WAYPOINTS = Option.<Waypoint>list()
        .comment("Sets the default waypoints to send to the player.")
        .node("default-waypoints").type(new TypeToken<List<Waypoint>>() {})
        .defaultValue(new ArrayList<>(Collections.singletonList(WaypointModule.SPAWN_WAYPOINT)))
        .build();

    /**
     * Lets servers handle waypoints.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> SERVER_HANDLES_WAYPOINTS = Option.<Boolean>builder()
        .comment("Set to 'true' to let servers handle waypoints, otherwise 'false'.")
        .node("server-handles-waypoints").type(TypeToken.get(Boolean.class))
        .defaultValue(false).notifyClient().build();

    WaypointModule() {
        this.registerOptions(
            WaypointModule.DEFAULT_WAYPOINTS,
            WaypointModule.SERVER_HANDLES_WAYPOINTS
        );
    }

    @Override
    public boolean isClientNotify() {
        return true;
    }

    /**
     * Displays the {@link Waypoint} to the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param waypoint   the waypoint
     * @since 1.0.0
     */
    public abstract void displayWaypoint(Recipients recipients, Waypoint waypoint);

    /**
     * Removes the {@link Waypoint} from the {@link Recipients}.
     *
     * @param recipients   the recipients that are receiving the packet
     * @param waypointName the waypoint name
     * @since 1.0.0
     */
    public abstract void removeWaypoint(Recipients recipients, String waypointName);

    /**
     * Removes the {@link Waypoint} from the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param waypoint   the waypoint
     * @since 1.0.0
     */
    public abstract void removeWaypoint(Recipients recipients, Waypoint waypoint);

    /**
     * Resets all {@link Waypoint}s for the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @since 1.0.0
     */
    public abstract void resetWaypoints(Recipients recipients);

}
