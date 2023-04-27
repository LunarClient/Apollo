package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.option.ListOption;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ui.Waypoint;
import com.lunarclient.apollo.world.ApolloBlockLocation;
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
public abstract class Waypoints extends ApolloModule {

    private static final Waypoint SPAWN_WAYPOINT = Waypoint.of(
        "Spawn",
        ApolloBlockLocation.of("world", 0, 100, 0),
        Color.RED,
        false,
        true
    );

    /**
     * Returns the default list of waypoints to show the player.
     *
     * @since 1.0.0
     */
    public static final ListOption<Waypoint> DEFAULT_WAYPOINTS = Option.<Waypoint>list()
        .comment("Sets the default waypoints to send to the player.")
        .node("default-waypoints").type(new TypeToken<List<Waypoint>>() {})
        .defaultValue(new ArrayList<>(Collections.singletonList(Waypoints.SPAWN_WAYPOINT)))
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

    Waypoints() {
        super("Waypoints");

        this.registerOptions(Waypoints.SERVER_HANDLES_WAYPOINTS);
    }

    @Override
    public boolean isClientNotify() {
        return true;
    }

    /**
     * Displays the {@link Waypoint} to the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param waypoint the waypoint
     * @since 1.0.0
     */
    public abstract void displayWaypoint(ApolloPlayer player, Waypoint waypoint);

    /**
     * Removes the {@link Waypoint} from the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param waypointName the waypoint name
     * @since 1.0.0
     */
    public abstract void removeWaypoint(ApolloPlayer player, String waypointName);

    /**
     * Removes the {@link Waypoint} from the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param waypoint the waypoint
     * @since 1.0.0
     */
    public abstract void removeWaypoint(ApolloPlayer player, Waypoint waypoint);

    /**
     * Resets all {@link Waypoint}s for the {@link ApolloPlayer}.
     *
     * @param player the player
     * @since 1.0.0
     */
    public abstract void resetWaypoints(ApolloPlayer player);

}
