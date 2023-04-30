package com.lunarclient.apollo.module.waypoint;

import com.lunarclient.apollo.event.player.ApolloRegisterPlayerEvent;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.waypoint.v1.DisplayWaypointMessage;
import com.lunarclient.apollo.waypoint.v1.RemoveWaypointMessage;
import com.lunarclient.apollo.waypoint.v1.ResetWaypointsMessage;
import java.util.List;
import lombok.NonNull;

/**
 * Provides the waypoints module.
 *
 * @since 1.0.0
 */
public final class WaypointModuleImpl extends WaypointModule {

    public WaypointModuleImpl() {
        super();
        this.handle(ApolloRegisterPlayerEvent.class, this::onPlayerRegister);
    }

    @Override
    public void displayWaypoint(@NonNull ApolloPlayer viewer, @NonNull Waypoint waypoint) {
        ((AbstractApolloPlayer) viewer).sendPacket(this.toProtobuf(waypoint));
    }

    @Override
    public void removeWaypoint(@NonNull ApolloPlayer viewer, @NonNull String waypointName) {
        ((AbstractApolloPlayer) viewer).sendPacket(RemoveWaypointMessage.newBuilder()
            .setName(waypointName)
            .build());
    }

    @Override
    public void removeWaypoint(@NonNull ApolloPlayer viewer, @NonNull Waypoint waypoint) {
        this.removeWaypoint(viewer, waypoint.getName());
    }

    @Override
    public void resetWaypoints(@NonNull ApolloPlayer viewer) {
        ((AbstractApolloPlayer) viewer).sendPacket(ResetWaypointsMessage.getDefaultInstance());
    }

    private void onPlayerRegister(ApolloRegisterPlayerEvent event) {
        ApolloPlayer player = event.getPlayer();
        List<Waypoint> waypoints = this.getOptions().get(player, WaypointModule.DEFAULT_WAYPOINTS);

        if (waypoints != null) {
            for (Waypoint waypoint : waypoints) {
                ((AbstractApolloPlayer) player).sendPacket(this.toProtobuf(waypoint));
            }
        }
    }

    private DisplayWaypointMessage toProtobuf(Waypoint waypoint) {
        return DisplayWaypointMessage.newBuilder()
            .setName(waypoint.getName())
            .setLocation(NetworkTypes.toProtobuf(waypoint.getLocation()))
            .setColor(NetworkTypes.toProtobuf(waypoint.getColor()))
            .setPreventRemoval(waypoint.isPreventRemoval())
            .setVisible(waypoint.isVisible())
            .build();
    }

}
