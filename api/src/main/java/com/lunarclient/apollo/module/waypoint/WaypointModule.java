package com.lunarclient.apollo.module.waypoint;

import com.lunarclient.apollo.common.location.ApolloBlockLocation;
import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.module.ModuleDefinition;
import com.lunarclient.apollo.option.ListOption;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.player.ApolloPlayer;
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
        .visible(true)
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
        this.registerOptions(WaypointModule.SERVER_HANDLES_WAYPOINTS);
    }

    @Override
    public boolean isClientNotify() {
        return true;
    }

    /**
     * Displays the {@link Waypoint} to the {@link ApolloPlayer}.
     *
     * @param viewer the player who is receiving the packet
     * @param waypoint the waypoint
     * @since 1.0.0
     */
    public abstract void displayWaypoint(ApolloPlayer viewer, Waypoint waypoint);

    /**
     * Removes the {@link Waypoint} from the {@link ApolloPlayer}.
     *
     * @param viewer the player who is receiving the packet
     * @param waypointName the waypoint name
     * @since 1.0.0
     */
    public abstract void removeWaypoint(ApolloPlayer viewer, String waypointName);

    /**
     * Removes the {@link Waypoint} from the {@link ApolloPlayer}.
     *
     * @param viewer the player who is receiving the packet
     * @param waypoint the waypoint
     * @since 1.0.0
     */
    public abstract void removeWaypoint(ApolloPlayer viewer, Waypoint waypoint);

    /**
     * Resets all {@link Waypoint}s for the {@link ApolloPlayer}.
     *
     * @param viewer the player who is receiving the packet
     * @since 1.0.0
     */
    public abstract void resetWaypoints(ApolloPlayer viewer);

}
