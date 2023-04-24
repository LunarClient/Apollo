package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.event.player.ApolloRegisterPlayerEvent;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ui.Waypoint;
import com.lunarclient.apollo.waypoint.v1.DisplayWaypointMessage;
import com.lunarclient.apollo.waypoint.v1.RemoveWaypointMessage;
import com.lunarclient.apollo.waypoint.v1.ResetWaypointsMessage;
import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Provides the waypoints module.
 *
 * @since 1.0.0
 */
public final class WaypointsImpl extends Waypoints {

    public WaypointsImpl() {
        super();

        this.handle(ApolloRegisterPlayerEvent.class, this::onPlayerRegister);
    }

    @Override
    public void displayWaypoint(ApolloPlayer player, Waypoint waypoint) {
        requireNonNull(player, "player");
        requireNonNull(waypoint, "waypoint");

        ((AbstractApolloPlayer) player).sendPacket(this.to(waypoint));
    }

    @Override
    public void removeWaypoint(ApolloPlayer player, String waypointName) {
        requireNonNull(player, "player");
        requireNonNull(waypointName, "waypointName");

        ((AbstractApolloPlayer) player).sendPacket(RemoveWaypointMessage.newBuilder()
            .setName(waypointName)
            .build());
    }

    @Override
    public void removeWaypoint(ApolloPlayer player, Waypoint waypoint) {
        requireNonNull(waypoint, "waypoint");

        this.removeWaypoint(player, waypoint.getName());
    }

    @Override
    public void resetWaypoints(ApolloPlayer player) {
        requireNonNull(player, "player");

        ((AbstractApolloPlayer) player).sendPacket(ResetWaypointsMessage.getDefaultInstance());
    }

    private void onPlayerRegister(ApolloRegisterPlayerEvent event) {
        ApolloPlayer player = event.getPlayer();
        List<Waypoint> waypoints = this.getOptions().get(player, Waypoints.DEFAULT_WAYPOINTS);

        if(waypoints == null) return;

        for(Waypoint waypoint : waypoints) {
            ((AbstractApolloPlayer) player).sendPacket(this.to(waypoint));
        }
    }

    private DisplayWaypointMessage to(Waypoint waypoint) {
        return DisplayWaypointMessage.newBuilder()
            .setName(waypoint.getName())
            .setLocation(NetworkTypes.toProtobuf(waypoint.getLocation()))
            .setColor(NetworkTypes.toProtobuf(waypoint.getColor()))
            .setPreventRemoval(waypoint.isPreventRemoval())
            .setVisible(waypoint.isVisible())
            .build();
    }

}
